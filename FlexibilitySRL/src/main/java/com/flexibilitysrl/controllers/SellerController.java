package com.flexibilitysrl.controllers;

import com.flexibilitysrl.entity.SellerEntity;
import com.flexibilitysrl.services.ISellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Seller")
public class SellerController {
    private static Logger logger = LoggerFactory.getLogger(CostumerController.class);

    @Autowired
    private ISellerService vendedorService;

    @PostMapping
    public ResponseEntity<?> saveSeller(@RequestBody SellerEntity sellerEntity) {
        SellerEntity clienteEntityDb = vendedorService.save(sellerEntity);
        logger.info("Nuevo Seller Creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteEntityDb);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(vendedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        SellerEntity cliente = vendedorService.findBy(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        vendedorService.deleteBy(id);
        logger.warn("Seller BORRADO");
        return ResponseEntity.ok().build();
    }


}
