package com.flexibilitysrl.controllers;

import com.flexibilitysrl.entity.VendedorEntity;
import com.flexibilitysrl.services.IVendedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/Vendedor")
public class VendedorController {
    private static Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private IVendedorService vendedorService;

    @PostMapping
    public ResponseEntity<?> saveVendedor(@RequestBody VendedorEntity vendedorEntity) {
        VendedorEntity clienteEntityDb = vendedorService.save(vendedorEntity);
        logger.info("Nuevo Vendedor Creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteEntityDb);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(vendedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<VendedorEntity> cliente = vendedorService.findBy(id);
        if (!cliente.isPresent()) {
            logger.error("ERROR Vendedor NO ENCONTRADO");
            return ResponseEntity.notFound().build();
        }
        logger.info("Vendedor ENCONTRADO");
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        vendedorService.deleteBy(id);
        logger.warn("Vendedor BORRADO");
        return ResponseEntity.ok().build();
    }


}
