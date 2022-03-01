package com.flexibilitysrl.controllers;

import com.flexibilitysrl.entity.CostumerEntity;
import com.flexibilitysrl.entity.ProductEntity;
import com.flexibilitysrl.entity.SalesEntity;
import com.flexibilitysrl.entity.SellerEntity;
import com.flexibilitysrl.models.RequestsSells;
import com.flexibilitysrl.services.ICostumerService;
import com.flexibilitysrl.services.IProductService;
import com.flexibilitysrl.services.ISellerService;
import com.flexibilitysrl.services.ISellsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1/Sales")
public class SalesController {
    private static final Logger logger = LoggerFactory.getLogger(SalesController.class);
    @Autowired
    private ISellsService ventasService;
    @Autowired
    private IProductService productoService;
    @Autowired
    private ICostumerService clienteService;
    @Autowired
    private ISellerService vendedorService;
    @PostMapping
    @RequestMapping(value = "/newsells")
    public ResponseEntity<SalesEntity> saveSells(@RequestBody RequestsSells requestsSells) {
        ProductEntity productEntity;
        List<ProductEntity> prodEntityList = new ArrayList<>();
        for (String prod : requestsSells.getIdProductsList()) {
            productEntity = (productoService.findById(prod));
            prodEntityList.add(productEntity);
        }

        CostumerEntity costumerEntity = clienteService.findBy(requestsSells.getIdClient());
        SellerEntity sellerEntity = vendedorService.findBy(requestsSells.getIdSeller());
        sellerEntity.setCantVentas((vendedorService.findBy(sellerEntity.getIdVendedor()).getCantVentas() + 1));
        SalesEntity ventaEntityDb = ventasService.saveVentas(requestsSells.getSalesEntity(), prodEntityList, costumerEntity, sellerEntity);
        logger.info("New Sell Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaEntityDb);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        SalesEntity venta = ventasService.findByIdVenta(id);
        return ResponseEntity.ok(venta);
    }

    @GetMapping("/listAll")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok().body(ventasService.findAllVentas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        ventasService.deleteVentas(id);
        logger.warn("Sell Erased");
        return ResponseEntity.ok().build();
    }
}
