package com.flexibilitysrl.controllers;
import com.flexibilitysrl.dao.SellerDAO;
import com.flexibilitysrl.entity.SellerEntity;
import com.flexibilitysrl.exception.ObjectNotFoundEx;
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
    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);

    @Autowired
    private ISellerService vendedorService;

    @PostMapping
    public ResponseEntity<Object> saveSeller(@RequestBody SellerDAO seller) throws ObjectNotFoundEx {
        SellerEntity sellerEntityResg = new SellerEntity();
        sellerEntityResg.setIdVendedor(seller.getIdVendedor());
        sellerEntityResg.setCantVentas(seller.getCantVentas());
        sellerEntityResg.setApellido(seller.getApellido());
        sellerEntityResg.setNombre(seller.getNombre());
        sellerEntityResg.setDni(seller.getDni());
        SellerEntity sellerEntityDb= vendedorService.save(sellerEntityResg);
        logger.info("New Seller Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerEntityDb);
    }

    @GetMapping("/listAll")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok().body(vendedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        SellerEntity cliente = vendedorService.findBy(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        vendedorService.deleteBy(id);
        logger.warn("Seller Erased");
        return ResponseEntity.ok().build();
    }


}
