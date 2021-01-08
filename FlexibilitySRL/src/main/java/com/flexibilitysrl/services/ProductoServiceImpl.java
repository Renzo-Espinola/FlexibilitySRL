package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ProductoEntity;
import com.flexibilitysrl.exception.ObjectNotFoundEx;
import com.flexibilitysrl.repositories.ProductoRepositories;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductoServiceImpl implements IProductoService {
    private static Logger logger = LoggerFactory.getLogger(ProductoServiceImpl.class);
    @Autowired
    private ProductoRepositories productoRepositories;

    @Override
    public Iterable<ProductoEntity> findAll() {
        return productoRepositories.findAll();
    }

    @Override
    public ProductoEntity save(ProductoEntity productoEntity) {
        return productoRepositories.save(productoEntity);
    }

    @Override
    public ProductoEntity findById(Long id) {
        return productoRepositories.findById(id).orElseThrow(() -> new ObjectNotFoundEx("ERROR PRODUCTO"));
    }

    @Override
    public void deleteBy(Long id) {
        String message = null;
        try {
            if (id != null) {
                productoRepositories.deleteById(id);
                message = "ID FOUND";

            }
        } catch (ObjectNotFoundEx ob) {
            logger.error(ob.getMessage());
        }
        System.out.println(message);

    }

    @Override
    public ProductoEntity updateProducto(ProductoEntity productoEntity) {
        ProductoEntity productoEntityDb = new ProductoEntity();
        productoEntityDb.setIdProducto(productoEntity.getIdProducto());
        productoEntityDb.setNombreProducto(productoEntity.getNombreProducto());
        productoEntityDb.setPrecio(productoEntity.getPrecio());
        productoEntityDb.setStock(productoEntity.getStock());
        productoEntityDb.setTipo(productoEntity.getTipo());
        return productoRepositories.save(productoEntityDb);

    }
}