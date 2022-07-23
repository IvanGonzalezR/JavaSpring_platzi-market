package com.platzi.market.domain.service;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Este archivo descaonoce el termino en lo que trabaja
//Estamos trabajando en terminos del dominio, no en la capa de persistencia

@Service
public class ProductService {
    //Autowired is used to inject the dependency
    //Solo si la clase es una clase de Spring, no si es una clase de Java
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(Long productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(Long categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(Long productId) {
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }
}
