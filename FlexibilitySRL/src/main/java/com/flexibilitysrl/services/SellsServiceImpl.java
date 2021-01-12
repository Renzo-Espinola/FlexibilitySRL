package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.CostumerEntity;
import com.flexibilitysrl.entity.ProductEntity;
import com.flexibilitysrl.entity.SellerEntity;
import com.flexibilitysrl.entity.SellsEntity;
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

    private static Logger logger = LoggerFactory.getLogger(SellsServiceImpl.class);

    @Override
    public SellsEntity saveVentas(SellsEntity sellsEntity,
                                  List<ProductEntity> productEntityOpt,
                                  CostumerEntity costumerEntityOpt,
                                  SellerEntity sellerEntityOpt) throws IllegalArgEx {
        String message = "";
        double totalCompra = 0;
        int i = 0;
        ProductEntity productEntity1 = new ProductEntity();
        SellsEntity sellsEntityDb = new SellsEntity();
        if (productEntityOpt.stream().findFirst().get().getStock() > 0 &&
                sellsEntity.getCantidad() <= productEntityOpt.stream().
                        findFirst().get().getStock()) {

            for (ProductEntity prod : productEntityOpt) {
                totalCompra = prod.getPrecio() + totalCompra;
                prod.setStock(prod.getStock()- sellsEntity.getCantidad());
                productEntityOpt.set(i,prod);
                i++;
            }
            sellsEntityDb.setProductoEntities(productEntityOpt);
            sellsEntityDb.setSellerEntity(sellerEntityOpt);
            sellsEntityDb.setCostumerEntity(costumerEntityOpt);
            sellsEntityDb.setIdVentas(sellsEntity.getIdVentas());
            sellsEntityDb.setCantidad(sellsEntity.getCantidad());
            sellsEntityDb.setTotal(totalCompra * sellsEntityDb.getCantidad());
        } else {
            throw new IllegalArgEx("INSUFFICIENT STOCK");
        }
        return sellsRepositories.save(sellsEntityDb);
    }

    @Override
    public void deleteVentas(Long id) {

    }

    @Override
    public SellsEntity updateVentas(SellsEntity sellsEntity) {
        return null;
    }

    @Override
    public Iterable<SellsEntity> findAllVentas() {
        return sellsRepositories.findAll();
    }

    @Override
    public SellsEntity findByIdVenta(Long id) {
        return sellsRepositories.findById(id).orElseThrow(() -> new IllegalArgEx("ERROR"));
    }
}
//totalCompra = ventasEntityDb.getProductoEntities().get(i).getPrecio() + totalCompra;
//productoEntityOpt.stream().forEach((p) -> {
                    /*ProductEntity producResg = new ProductEntity();
                    producResg.setIdProducto(p.getIdProducto());
                    producResg.setNombreProducto(p.getNombreProducto());
                    producResg.setTipo(p.getTipo());
                    producResg.setPrecio(p.getPrecio());
                    producResg.setStock(p.getStock());*/
