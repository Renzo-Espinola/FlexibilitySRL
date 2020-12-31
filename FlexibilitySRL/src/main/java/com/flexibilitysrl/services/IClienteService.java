package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ClientesEntity;
import com.flexibilitysrl.models.Clientes;

import java.util.Optional;

public interface IClienteService {
    Iterable<ClientesEntity>findAll();
    ClientesEntity save (ClientesEntity clientes);
    Optional<ClientesEntity> findBy(Long id);
    void deleteBy (Long id);
    ClientesEntity updateTask(ClientesEntity clientesEntity);
}
