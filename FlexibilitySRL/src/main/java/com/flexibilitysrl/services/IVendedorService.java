package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.VendedorEntity;

public interface IVendedorService {
    Iterable<VendedorEntity>findAll();
    VendedorEntity save (VendedorEntity vendedorEntity);
    VendedorEntity findBy(Long id);
    void deleteBy (Long id);
    VendedorEntity updateTask(VendedorEntity vendedorEntity);
}


