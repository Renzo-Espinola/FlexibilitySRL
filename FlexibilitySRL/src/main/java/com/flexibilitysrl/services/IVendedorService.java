package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.VendedorEntity;

import java.util.Optional;

public interface IVendedorService {
    Iterable<VendedorEntity>findAll();
    VendedorEntity save (VendedorEntity vendedorEntity);
    Optional<VendedorEntity> findBy(Long id);
    void deleteBy (Long id);
    VendedorEntity updateTask(VendedorEntity vendedorEntity);
}


