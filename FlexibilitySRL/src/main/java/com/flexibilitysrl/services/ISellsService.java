package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.CostumerEntity;
import com.flexibilitysrl.entity.ProductEntity;
import com.flexibilitysrl.entity.SellerEntity;
import com.flexibilitysrl.entity.SellsEntity;
import com.flexibilitysrl.exception.ObjectNotFoundEx;

import java.util.List;

public interface ISellsService {
    SellsEntity saveVentas (SellsEntity sellsEntity,
                            List<ProductEntity> productEntity,
                            CostumerEntity costumerEntity,
                            SellerEntity sellerEntity)throws ObjectNotFoundEx;
    void deleteVentas(Long id);
    SellsEntity updateVentas (SellsEntity sellsEntity);
    Iterable<SellsEntity>findAllVentas();
    SellsEntity findByIdVenta(Long id);

}
