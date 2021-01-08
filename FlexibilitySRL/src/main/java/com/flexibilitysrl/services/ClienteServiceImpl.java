package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ClientesEntity;
import com.flexibilitysrl.exception.ObjectNotFoundEx;
import com.flexibilitysrl.repositories.ClienteRepositories;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClienteServiceImpl implements IClienteService {

    private static Logger logger = LoggerFactory.getLogger(IClienteService.class);
    @Autowired
    private ClienteRepositories clienteRepositories;

    @Override
    public Iterable<ClientesEntity> findAll() {
        return clienteRepositories.findAll();
    }

    @Override
    public ClientesEntity save(ClientesEntity clientes) {
        return clienteRepositories.save(clientes);
    }

    @Override
    public ClientesEntity findBy(Long id) {
        return clienteRepositories.findById(id).orElseThrow(() -> new ObjectNotFoundEx("ERROR CLIENTE"));
    }

    @Override
    public void deleteBy(Long id) {
        String message = null;
        try {
            if (id != null) {
                clienteRepositories.deleteById(id);
                message = "ID FOUND";
            }

        } catch (ObjectNotFoundEx ob) {
            String messageObError = ob.getMessage();
        }
        System.out.println(message);


    }

    @Override
    public ClientesEntity updateTask(ClientesEntity clientesEntity) {
        ClientesEntity clientesEntityDb = new ClientesEntity();
        clientesEntityDb.setIdCliente(clientesEntity.getIdCliente());
        clientesEntityDb.setRazonSocial(clientesEntity.getRazonSocial());
        return clienteRepositories.save(clientesEntityDb);
    }
}
