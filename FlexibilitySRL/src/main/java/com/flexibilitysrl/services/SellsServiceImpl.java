package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.CostumerEntity;
import com.flexibilitysrl.entity.ProductEntity;
import com.flexibilitysrl.entity.SalesEntity;
import com.flexibilitysrl.entity.SellerEntity;
import com.flexibilitysrl.exception.IllegalArgEx;
import com.flexibilitysrl.repositories.CostumerRepositories;
import com.flexibilitysrl.repositories.ProductRepositories;
import com.flexibilitysrl.repositories.SellerRepositories;
import com.flexibilitysrl.repositories.SellsRepositories;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SellsServiceImpl implements ISellsService {
    @Autowired
    private SellsRepositories sellsRepositories;
    @Autowired
    private CostumerRepositories costumerRepositories;
    @Autowired
    private SellerRepositories sellerRepositories;
    @Autowired
    private ProductRepositories productRepositories;

    private static final Logger logger = LoggerFactory.getLogger(SellsServiceImpl.class);

    @Override
    public SalesEntity saveVentas(SalesEntity salesEntity,
                                  List<ProductEntity> productEntityOpt,
                                  CostumerEntity costumerEntityOpt,
                                  SellerEntity sellerEntityOpt) throws IllegalArgEx {

        double totalCompra = 0;
        int i = 0;
        SalesEntity salesEntityDb = new SalesEntity();
        if (productEntityOpt.stream().findFirst().get().getStock() > 0 &&
                salesEntity.getCantidad() <= productEntityOpt.stream().
                        findFirst().get().getStock()) {

            for (ProductEntity prod : productEntityOpt) {
                totalCompra = prod.getPrecio() + totalCompra;
                prod.setStock(prod.getStock() - salesEntity.getCantidad());
                productEntityOpt.set(i, prod);
                i++;
            }
            salesEntityDb.setProductoEntities(productEntityOpt);
            salesEntityDb.setSellerEntity(sellerEntityOpt);
            salesEntityDb.setCostumerEntity(costumerEntityOpt);
            salesEntityDb.setIdVentas(salesEntity.getIdVentas());
            salesEntityDb.setCantidad(salesEntity.getCantidad());
            salesEntityDb.setTotal(totalCompra * salesEntityDb.getCantidad());
        } else {
            logger.warn("INSUFFICIENT STOCK");
            throw new IllegalArgEx("INSUFFICIENT STOCK");
        }
        return sellsRepositories.save(salesEntityDb);
    }

    @Override
    public void deleteVentas(Long id) {
        String message = null;
        try {
            if (id != null) {
                sellsRepositories.deleteById(id);
                message = "ID FOUND";
            }

        } catch (IllegalArgEx ob) {
            ob.getMessage();
        }
        logger.info(message);
    }


    @Override
    public SalesEntity updateVentas(SalesEntity salesEntity) {
        SalesEntity salesEntityDb = new SalesEntity();
        salesEntityDb.setCantidad(salesEntity.getCantidad());
        salesEntityDb.setCostumerEntity(salesEntity.getCostumerEntity());
        salesEntityDb.setFechaCompra(salesEntity.getFechaCompra());
        salesEntityDb.setIdVentas(salesEntity.getIdVentas());
        salesEntityDb.setProductoEntities(salesEntity.getProductoEntities());
        salesEntityDb.setSellerEntity(salesEntity.getSellerEntity());
        salesEntityDb.setTotal(salesEntity.getTotal());
        return sellsRepositories.save(salesEntityDb);
    }

    @Override
    public Iterable<SalesEntity> findAllVentas() {
        return sellsRepositories.findAll();
    }

    @Override
    public SalesEntity findByIdVenta(Long id) {
        return sellsRepositories.findById(id).orElseThrow(() -> new IllegalArgEx("ERROR"));
    }
}

