package com.flexibilitysrl.controllers;

import com.flexibilitysrl.dao.CostumerDAO;
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
    private static final Logger logger = LoggerFactory.getLogger(CostumerController.class);

    @Autowired
    private ICostumerService clienteService;

    @PostMapping
    public ResponseEntity<CostumerEntity> saveCostumer(@RequestBody CostumerDAO cliente) {
        CostumerEntity clienteEntityDb1 = new CostumerEntity();
        clienteEntityDb1.setIdCliente(cliente.getIdCliente());
        clienteEntityDb1.setRazonSocial(cliente.getRazonSocial());
        clienteEntityDb1.setApellido(cliente.getApellido());
        clienteEntityDb1.setNombre(cliente.getNombre());
        clienteEntityDb1.setDni(cliente.getDni());
        CostumerEntity costumerEntityDb = clienteService.save(clienteEntityDb1);
        logger.info("New Costumer Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(costumerEntityDb);
    }

    @GetMapping("/listAll")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostumerEntity> findById(@PathVariable Long id) {
        CostumerEntity cliente = clienteService.findBy(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CostumerEntity> deleteById(@PathVariable Long id) {
        clienteService.deleteBy(id);
        logger.warn("Costumer deleted");
        return ResponseEntity.ok().build();
    }


}
