package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ProductoEntity;

import java.util.Optional;

public interface IProductoService {
    Iterable<ProductoEntity>findAll();
    ProductoEntity save (ProductoEntity productoEntity);
    Optional<ProductoEntity> findBy(Long id);
    void deleteBy (Long id);
    ProductoEntity updateProducto(ProductoEntity productoEntity);
}
