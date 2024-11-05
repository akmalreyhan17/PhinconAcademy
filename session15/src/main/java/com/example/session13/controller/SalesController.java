package com.example.session13.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.session13.model.entity.Sales;
import com.example.session13.service.SalesService;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping
    public ResponseEntity<List<Sales>> getAllSales() {
        List<Sales> salesList = salesService.getAllSales();
        return ResponseEntity.ok().body(salesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSalesById(@PathVariable Long id) {
        Sales sales = salesService.getSalesById(id);
        return ResponseEntity.ok().body(sales);
    }

    @PostMapping
    public ResponseEntity<Sales> createSales(@RequestParam Long carId, @RequestParam Long customerId,
            @RequestBody Sales sales) {
        Sales createdSales = salesService.createSales(carId, customerId, sales);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSales);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sales> updateSales(@PathVariable Long id, @RequestBody Sales salesDetails) {
        Sales updatedSales = salesService.updateSales(id, salesDetails);
        return ResponseEntity.ok().body(updatedSales);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Sales> patchSales(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Sales updatedSales = salesService.patchSales(id, updates);
        return ResponseEntity.ok().body(updatedSales);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSales(@PathVariable Long id) {
        salesService.deleteSales(id);
        return ResponseEntity.noContent().build();
    }
}
