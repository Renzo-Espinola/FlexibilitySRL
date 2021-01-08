package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.VendedorEntity;
import com.flexibilitysrl.exception.ObjectNotFoundEx;
import com.flexibilitysrl.repositories.VendedorRepositories;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VendedorServiceImpl implements IVendedorService {
    private static Logger logger = LoggerFactory.getLogger(IClienteService.class);
    @Autowired
    private VendedorRepositories vendedorRepositories;
    
    @Override
    public Iterable<VendedorEntity> findAll() {
        return vendedorRepositories.findAll();
    }

    @Override
    public VendedorEntity save(VendedorEntity vendedorEntity) {
        return vendedorRepositories.save(vendedorEntity);
    }

    @Override
    public VendedorEntity findBy(Long id) {
        return vendedorRepositories.findById(id).orElseThrow(()-> new ObjectNotFoundEx("ERROR VENTAS"));

    }

    @Override
    public void deleteBy(Long id) {
        String message = null;
        try {
            if (id != null) {
                vendedorRepositories.deleteById(id);
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
        vendedorEntityDb.setCantVentas(vendedorEntity.getCantVentas()+1);
        return vendedorRepositories.save(vendedorEntityDb);
    }
}
