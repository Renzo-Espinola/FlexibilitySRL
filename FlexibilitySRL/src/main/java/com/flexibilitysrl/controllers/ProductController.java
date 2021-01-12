package com.flexibilitysrl.controllers;

import com.flexibilitysrl.entity.ProductEntity;
import com.flexibilitysrl.services.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Product")
public class ProductController {
    private static Logger logger = LoggerFactory.getLogger(CostumerController.class);

    @Autowired
    private IProductService productoService;
    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductEntity productEntity) {
        ProductEntity productEntityDb = productoService.save(productEntity);
        logger.info("New Product created");
        return ResponseEntity.status(HttpStatus.CREATED).body(productEntityDb);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProductEntity producto = productoService.findById(String.valueOf(id));
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        productoService.deleteBy(id);
        logger.warn("Product Erased");
        return ResponseEntity.ok().build();
    }

}
