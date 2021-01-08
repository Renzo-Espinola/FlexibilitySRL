package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ClientesEntity;

public interface IClienteService {
    Iterable<ClientesEntity>findAll();
    ClientesEntity save (ClientesEntity clientes);
    ClientesEntity findBy(Long id);
    void deleteBy (Long id);
    ClientesEntity updateTask(ClientesEntity clientesEntity);
}
