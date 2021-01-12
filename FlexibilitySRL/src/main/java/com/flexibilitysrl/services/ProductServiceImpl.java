package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ProductEntity;
import com.flexibilitysrl.exception.IllegalArgEx;
import com.flexibilitysrl.repositories.ProductRepositories;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepositories productRepositories;

    @Override
    public Iterable<ProductEntity> findAll() {
        return productRepositories.findAll();
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return productRepositories.save(productEntity);
    }

    @Override
    public ProductEntity findById(String id) {
        return productRepositories.findById(id).orElseThrow(() -> new IllegalArgEx("PRODUCT ERROR "));
    }

    @Override
    public void deleteBy(Long id) {
        String message = null;
        try {
            if (id != null) {
                productRepositories.deleteById(String.valueOf(id));
                message = "ID FOUND";

            }
        } catch (IllegalArgEx ob) {
            logger.error(ob.getMessage());
        }
        System.out.println(message);

    }

    @Override
    public ProductEntity updateProducto(ProductEntity productEntity) {
        ProductEntity productEntityDb = new ProductEntity();
        productEntityDb.setIdProducto(productEntity.getIdProducto());
        productEntityDb.setNombreProducto(productEntity.getNombreProducto());
        productEntityDb.setPrecio(productEntity.getPrecio());
        productEntityDb.setStock(productEntity.getStock());
        productEntityDb.setTipo(productEntity.getTipo());
        return productRepositories.save(productEntityDb);

    }
}