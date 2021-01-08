package com.flexibilitysrl.controllers;

import com.flexibilitysrl.entity.ClientesEntity;
import com.flexibilitysrl.entity.ProductoEntity;
import com.flexibilitysrl.entity.VendedorEntity;
import com.flexibilitysrl.entity.VentasEntity;
import com.flexibilitysrl.services.IClienteService;
import com.flexibilitysrl.services.IProductoService;
import com.flexibilitysrl.services.IVendedorService;
import com.flexibilitysrl.services.IVentasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "v1/Ventas")
public class VentasController {
    
    private static Logger logger = LoggerFactory.getLogger(VentasController.class);
    
    @Autowired
    private IVentasService ventasService;
    @Autowired
    private IProductoService productoService;
    @Autowired
    private IClienteService clienteService;
    @Autowired
    private IVendedorService vendedorService;


    @PostMapping
    @RequestMapping(value ="v1/Producto/{idProducto}/Cliente/{idCliente}/Vendedor/{idVendedor}",method = RequestMethod.POST)
    public ResponseEntity<?> saveVenta (@RequestBody VentasEntity ventasEntity, @PathVariable("idProducto") Long idProducto,@PathVariable("idCliente") Long idCliente,@PathVariable("idVendedor") Long idvendedor){
        List<ProductoEntity> prodEntityList= new ArrayList<>();
        prodEntityList.add(productoService.findById(idProducto));
        ClientesEntity clientesEntity = clienteService.findBy(idCliente);
        VendedorEntity vendedorEntity = vendedorService.findBy(idvendedor);
        vendedorEntity.setCantVentas((vendedorService.findBy(idvendedor).getCantVentas()+1));
        VentasEntity ventaEntityDb = ventasService.saveVentas(ventasEntity,prodEntityList,clientesEntity,vendedorEntity);
        logger.info("Nueva Venta Creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaEntityDb);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        VentasEntity venta = ventasService.findByIdVenta(id);
        return ResponseEntity.ok(venta);
    }
    @GetMapping("/listAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(ventasService.findAllVentas());
    }
    
}
