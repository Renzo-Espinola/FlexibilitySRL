package com.flexibilitysrl.controllers;

import com.flexibilitysrl.entity.ClientesEntity;
import com.flexibilitysrl.services.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/Cliente")
public class ClienteController {
    private static Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private IClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> saveCliente(@RequestBody ClientesEntity cliente) {
        ClientesEntity clienteEntityDb = clienteService.save(cliente);
        logger.info("Nuevo Cliente Creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteEntityDb);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ClientesEntity cliente = clienteService.findBy(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        clienteService.deleteBy(id);
        logger.warn("CLIENTE BORRADO");
        return ResponseEntity.ok().build();
    }


}
