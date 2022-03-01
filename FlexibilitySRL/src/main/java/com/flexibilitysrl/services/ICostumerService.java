package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.CostumerEntity;

public interface ICostumerService {
    Iterable<CostumerEntity>findAll();
    CostumerEntity save (CostumerEntity clientes);
    CostumerEntity findBy(Long id);
    void deleteBy (Long id);
    CostumerEntity updateTask(CostumerEntity costumerEntity);
}
