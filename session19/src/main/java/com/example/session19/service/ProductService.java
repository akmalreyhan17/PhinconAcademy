package com.example.session19.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.example.session19.model.Product;
import com.example.session19.repository.ProductRepository;

@Service
@CacheConfig(cacheNames = "product")
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    
    @Cacheable(value = "product", key = "#id", unless = "#result.name > 1000")
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Caching(
        evict = {@CacheEvict(value = "productList", allEntries = true)},
        put = {@CachePut(value = "product", key = "#id")}
    )
    public Product updateProduct(Product product, Long id) {
        Optional<Product> nProduct = productRepository.findById(id);
        if (nProduct.isPresent()) {
            Product pp = nProduct.get();
            pp.setName(product.getName());
            pp.setPrice(product.getPrice());
            return productRepository.save(pp);
        }
        return null;
    }

    @CacheEvict(value = "product", key = "#id", beforeInvocation = true, allEntries = true)
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public void performAction() {
        logger.info("Performing an action...");

        try {
            logger.debug("Attempting to perform a complex operation...");
            // Simulate a complex operation
            int result = 1;
            logger.info("Operation completed successfully with result: {}", result);
        } catch (Exception e) {
            logger.error("An error occurred during operation: {}", e.getMessage(), e);
        }

        logger.warn("This is a warning message, something might need attention.");
    }

}
