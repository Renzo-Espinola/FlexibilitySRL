package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ProductEntity;

public interface IProductService {
    Iterable<ProductEntity>findAll();
    ProductEntity save (ProductEntity product);
    ProductEntity findById(String id);
    void deleteBy (Long id);
    ProductEntity updateProducto(ProductEntity productEntity);
}
