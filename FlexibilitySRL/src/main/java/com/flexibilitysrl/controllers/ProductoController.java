package com.flexibilitysrl.controllers;

import com.flexibilitysrl.entity.ProductoEntity;
import com.flexibilitysrl.services.IProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Producto")
public class ProductoController {
    private static Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private IProductoService productoService;
    @PostMapping
    public ResponseEntity<?> saveProducto(@RequestBody ProductoEntity productoEntity) {
        ProductoEntity productoEntityDb = productoService.save(productoEntity);
        logger.info("Nuevo producto Creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(productoEntityDb);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProductoEntity producto = productoService.findById(id);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        productoService.deleteBy(id);
        logger.warn("PRODUCTO BORRADO");
        return ResponseEntity.ok().build();
    }

}
