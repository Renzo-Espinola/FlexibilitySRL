package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ClientesEntity;
import com.flexibilitysrl.exceptions.ObjectNotFoundEx;
import com.flexibilitysrl.models.Clientes;
import com.flexibilitysrl.repositories.ClienteRepo;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ClienteServiceImpl implements IClienteService {

    private static Logger logger = LoggerFactory.getLogger(IClienteService.class);
    @Autowired
    private ClienteRepo clienteRepo;

    @Override
    public Iterable<ClientesEntity> findAll() {
        return clienteRepo.findAll();
    }

    @Override
    public ClientesEntity save(ClientesEntity clientes) {
        return clienteRepo.save(clientes);
    }

    @Override
    public Optional<ClientesEntity> findBy(Long id) {
        Optional<ClientesEntity> message = null;
        try {
            if (id != null) {
                message = clienteRepo.findById(id);

            }
        } catch (ObjectNotFoundEx ob) {
            logger.error(ob.getMessage());
        }
        return message;
    }

    @Override
    public void deleteBy(Long id) {
        String message = null;
        try {
            if (id != null) {
                clienteRepo.deleteById(id);
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
            return clienteRepo.save(clientesEntityDb);
    }
}
