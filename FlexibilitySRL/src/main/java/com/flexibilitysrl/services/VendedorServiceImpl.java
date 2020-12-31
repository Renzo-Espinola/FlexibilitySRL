package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.VendedorEntity;
import com.flexibilitysrl.exceptions.ObjectNotFoundEx;
import com.flexibilitysrl.repositories.VendedorRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class VendedorServiceImpl implements IVendedorService {
    private static Logger logger = LoggerFactory.getLogger(IClienteService.class);
    @Autowired
    private VendedorRepo vendedorRepo;
    
    @Override
    public Iterable<VendedorEntity> findAll() {
        return vendedorRepo.findAll();
    }

    @Override
    public VendedorEntity save(VendedorEntity vendedorEntity) {
        return vendedorRepo.save(vendedorEntity);
    }

    @Override
    public Optional<VendedorEntity> findBy(Long id) {
        Optional<VendedorEntity> message = null;
        try {
            if (id != null) {
                message = vendedorRepo.findById(id);

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
                vendedorRepo.deleteById(id);
                message = "ID FOUND";
            }

        } catch (ObjectNotFoundEx ob) {
            String messageObError = ob.getMessage();
        }
        System.out.println(message);
    }

    @Override
    public VendedorEntity updateTask(VendedorEntity vendedorEntity) {
       VendedorEntity vendedorEntityDb= new VendedorEntity();
        vendedorEntityDb.setIdVendedor(vendedorEntity.getIdVendedor());
        vendedorEntityDb.setCantVentas(vendedorEntity.getCantVentas());
        return vendedorRepo.save(vendedorEntityDb);
    }
}
