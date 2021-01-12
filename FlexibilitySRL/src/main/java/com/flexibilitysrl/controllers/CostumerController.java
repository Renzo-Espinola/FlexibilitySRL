package com.flexibilitysrl.controllers;

import com.flexibilitysrl.entity.CostumerEntity;
import com.flexibilitysrl.services.ICostumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/Costumer")
public class CostumerController {
    private static Logger logger = LoggerFactory.getLogger(CostumerController.class);

    @Autowired
    private ICostumerService clienteService;

    @PostMapping
    public ResponseEntity<?> saveCostumer(@RequestBody CostumerEntity cliente) {
        CostumerEntity clienteEntityDb = clienteService.save(cliente);
        logger.info("Nuevo Cliente Creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteEntityDb);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        CostumerEntity cliente = clienteService.findBy(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        clienteService.deleteBy(id);
        logger.warn("CLIENTE BORRADO");
        return ResponseEntity.ok().build();
    }


}
