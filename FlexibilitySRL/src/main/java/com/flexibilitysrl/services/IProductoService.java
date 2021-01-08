package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ProductoEntity;

public interface IProductoService {
    Iterable<ProductoEntity>findAll();
    ProductoEntity save (ProductoEntity productoEntity);
    ProductoEntity findById(Long id);
    void deleteBy (Long id);
    ProductoEntity updateProducto(ProductoEntity productoEntity);
}
