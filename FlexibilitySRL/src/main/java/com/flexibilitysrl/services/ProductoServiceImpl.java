package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ProductoEntity;
import com.flexibilitysrl.exceptions.ObjectNotFoundEx;
import com.flexibilitysrl.repositories.ProductoRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductoServiceImpl implements IProductoService {
    private static Logger logger = LoggerFactory.getLogger(ProductoServiceImpl.class);
    @Autowired
    private ProductoRepo productoRepo;

    @Override
    public Iterable<ProductoEntity> findAll() {
        return productoRepo.findAll();
    }

    @Override
    public ProductoEntity save (ProductoEntity productoEntity) {
        return productoRepo.save(productoEntity);
    }

    @Override
    public Optional<ProductoEntity> findById(Long id) {
        Optional<ProductoEntity> message = null;
        try {
            if (id != null) {
                message = productoRepo.findById(id);

            }
        } catch (ObjectNotFoundEx ob) {
            logger.error(ob.getMessage());
        }
        return message;
    }

    @Override
    public void deleteBy(Long id) {
        String message = null;
        try {
            if (id != null) {
                productoRepo.deleteById(id);
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
        return productoRepo.save(productoEntityDb);

    }
}