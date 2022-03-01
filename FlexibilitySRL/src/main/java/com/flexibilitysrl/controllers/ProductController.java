package com.flexibilitysrl.controllers;

import com.flexibilitysrl.dao.ProductDAO;
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
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService productoService;
    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody ProductDAO product) {
        ProductEntity productEntityResg = new ProductEntity();
        productEntityResg.setIdProducto(product.getIdProducto());
        productEntityResg.setNombreProducto(product.getNombreProducto());
        productEntityResg.setPrecio(product.getPrecio());
        productEntityResg.setStock(product.getStock());
        productEntityResg.setTipo(product.getTipo());
        ProductEntity productEntityDb = productoService.save(productEntityResg);
        logger.info("New Product created");
        return ResponseEntity.status(HttpStatus.CREATED).body(productEntityDb);
    }

    @GetMapping("/listAll")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok().body(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        ProductEntity producto = productoService.findById(String.valueOf(id));
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        productoService.deleteBy(id);
        logger.warn("Product Erased");
        return ResponseEntity.ok().build();
    }

}
