package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.CostumerEntity;
import com.flexibilitysrl.entity.ProductEntity;
import com.flexibilitysrl.entity.SalesEntity;
import com.flexibilitysrl.entity.SellerEntity;

import java.util.List;

public interface ISellsService {
    SalesEntity saveVentas (SalesEntity salesEntity,
                            List<ProductEntity> productEntity,
                            CostumerEntity costumerEntity,
                            SellerEntity sellerEntity);
    void deleteVentas(Long id);
    SalesEntity updateVentas (SalesEntity salesEntity);
    Iterable<SalesEntity>findAllVentas();
    SalesEntity findByIdVenta(Long id);

}
