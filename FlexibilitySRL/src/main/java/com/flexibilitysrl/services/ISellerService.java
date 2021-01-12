package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.SellerEntity;

public interface ISellerService {
    Iterable<SellerEntity>findAll();
    SellerEntity save (SellerEntity sellerEntity);
    SellerEntity findBy(Long id);
    void deleteBy (Long id);
    SellerEntity updateTask(SellerEntity sellerEntity);
}


