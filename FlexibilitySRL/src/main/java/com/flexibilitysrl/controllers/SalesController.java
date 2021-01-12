package com.flexibilitysrl.controllers;

import com.flexibilitysrl.entity.CostumerEntity;
import com.flexibilitysrl.entity.ProductEntity;
import com.flexibilitysrl.entity.SellerEntity;
import com.flexibilitysrl.entity.SellsEntity;
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

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(value = "v1/Sales")
public class SalesController {

    private static Logger logger = LoggerFactory.getLogger(SalesController.class);

    @Autowired
    private ISellsService ventasService;
    @Autowired
    private IProductService productoService;
    @Autowired
    private ICostumerService clienteService;
    @Autowired
    private ISellerService vendedorService;
    private ProductEntity productEntity;

    @PostMapping
    @RequestMapping(value = "/newsells", method = POST)
    public ResponseEntity<?> saveSells(@RequestBody RequestsSells requestsSells) {
        List<ProductEntity> prodEntityList = new ArrayList<>();
        for(String prod: requestsSells.getIdProductsList()){
        productEntity =(productoService.findById(prod));
        prodEntityList.add(productEntity);
        }

        CostumerEntity costumerEntity = clienteService.findBy(requestsSells.getIdClient());
        SellerEntity sellerEntity = vendedorService.findBy(requestsSells.getIdSeller());
        sellerEntity.setCantVentas((vendedorService.findBy(sellerEntity.getIdVendedor()).getCantVentas() + 1));
        SellsEntity ventaEntityDb = ventasService.saveVentas(requestsSells.getSellsEntity(),prodEntityList, costumerEntity, sellerEntity);
        logger.info("Nueva Venta Creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaEntityDb);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        SellsEntity venta = ventasService.findByIdVenta(id);
        return ResponseEntity.ok(venta);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(ventasService.findAllVentas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        ventasService.deleteVentas(id);
        logger.warn("Venta BORRADO");
        return ResponseEntity.ok().build();
    }
}
  /*  @PostMapping
    @RequestMapping(value ="v1/Producto/{idProducto}/Cliente/{idCliente}/Seller/{idVendedor}",method = RequestMethod.POST)
    public ResponseEntity<?> saveVenta (@RequestBody SellsEntity ventasEntity, @PathVariable("idProducto") Long idProducto,@PathVariable("idCliente") Long idCliente,@PathVariable("idVendedor") Long idvendedor){
        List<ProductEntity> prodEntityList= new ArrayList<>();
        prodEntityList.add(productoService.findById(idProducto));
        CostumerEntity clientesEntity = clienteService.findBy(idCliente);
        SellerEntity vendedorEntity = vendedorService.findBy(idvendedor);
        vendedorEntity.setCantVentas((vendedorService.findBy(idvendedor).getCantVentas()+1));
        SellsEntity ventaEntityDb = ventasService.saveVentas(ventasEntity,prodEntityList,clientesEntity,vendedorEntity);
        logger.info("Nueva Venta Creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaEntityDb);
    }*/