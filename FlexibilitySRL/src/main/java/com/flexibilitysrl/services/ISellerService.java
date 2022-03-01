package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.SellerEntity;
import com.flexibilitysrl.exception.ObjectNotFoundEx;

public interface ISellerService {
    Iterable<SellerEntity>findAll();
    SellerEntity save (SellerEntity sellerEntity) throws ObjectNotFoundEx;
    SellerEntity findBy(Long id);
    void deleteBy (Long id);
    SellerEntity updateTask(SellerEntity sellerEntity);
}


