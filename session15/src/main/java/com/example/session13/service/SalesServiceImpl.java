package com.example.session13.service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.example.session13.model.entity.Car;
import com.example.session13.model.entity.Customer;
import com.example.session13.model.entity.Sales;
import com.example.session13.model.exception.ResourceNotFoundException;
import com.example.session13.repository.CarRepository;
import com.example.session13.repository.CustomerRepository;
import com.example.session13.repository.SalesRepository;

@Service
public class SalesServiceImpl implements SalesService {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SalesRepository salesRepository;

    @Autowired
    public SalesServiceImpl(CarRepository carRepository, CustomerRepository customerRepository,
            SalesRepository salesRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.salesRepository = salesRepository;
    }

    @Override
    @Cacheable("sales")
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    @Cacheable(value = "sales", key = "#id")
    public Sales getSalesById(Long id) {
        return salesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales", id));
    }

    @Transactional
    @CacheEvict(value = "sales", allEntries = true)
    public Sales createSales(Long carId, Long customerId, Sales sales) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", carId));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", customerId));

        int creditScoreChange = calculateCreditScore(car);
        customer.setCreditScore(customer.getCreditScore() + creditScoreChange);
        customerRepository.save(customer);

        sales.setCar(car);
        sales.setCustomer(customer);
        return salesRepository.save(sales);
    }

    private int calculateCreditScore(Car car) {
        int credits = (int) (car.getPrice() / 1000000);
        return credits;
    }

    @CacheEvict(value = "sales", key = "#id")
    public Sales updateSales(Long id, Sales salesDetails) {
        Sales sales = salesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales", id));

        Car car = carRepository.findById(salesDetails.getCar().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Car", salesDetails.getCar().getId()));
        Customer customer = customerRepository.findById(salesDetails.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", salesDetails.getCustomer().getId()));

        sales.setCar(car);
        sales.setCustomer(customer);
        sales.setSaleDate(salesDetails.getSaleDate());
        sales.setSalesName(salesDetails.getSalesName());
        return salesRepository.save(sales);
    }

    @CacheEvict(value = "sales", key = "#id")
    public Sales patchSales(Long id, Map<String, Object> updates) {
        Sales sales = salesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales", id));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Sales.class, key);
            field.setAccessible(true);
            if (field.getType().equals(LocalDateTime.class)) {
                value = LocalDateTime.parse(value.toString(), DateTimeFormatter.ISO_DATE_TIME);
            }
            ReflectionUtils.setField(field, sales, value);
        });

        return salesRepository.save(sales);
    }

    @CacheEvict(value = "sales", key = "#id")
    public void deleteSales(Long id) {
        Sales sales = salesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sales", id));
        salesRepository.delete(sales);
    }
}
