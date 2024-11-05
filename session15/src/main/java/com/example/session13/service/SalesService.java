package com.example.session13.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.session13.model.entity.Sales;

@Service
public interface SalesService {
    List<Sales> getAllSales();
    Sales getSalesById(Long id);
    Sales createSales(Long carId, Long customerId, Sales sales);
    Sales updateSales(Long id, Sales salesDetails);
    Sales patchSales(Long id, Map<String, Object> updates);
    void deleteSales(Long id);
}


