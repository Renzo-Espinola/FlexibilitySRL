package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.CostumerEntity;
import com.flexibilitysrl.exception.ObjectNotFoundEx;
import com.flexibilitysrl.repositories.CostumerRepositories;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CostumerServiceImpl implements ICostumerService {

    private static Logger logger = LoggerFactory.getLogger(ICostumerService.class);
    @Autowired
    private CostumerRepositories costumerRepositories;

    @Override
    public Iterable<CostumerEntity> findAll() {
        return costumerRepositories.findAll();
    }

    @Override
    public CostumerEntity save(CostumerEntity clientes) {
        return costumerRepositories.save(clientes);
    }

    @Override
    public CostumerEntity findBy(Long id) {
        return costumerRepositories.findById(id).orElseThrow(() -> new ObjectNotFoundEx("ERROR CLIENTE"));
    }

    @Override
    public void deleteBy(Long id) {
        String message = null;
        try {
            if (id != null) {
                costumerRepositories.deleteById(id);
                message = "ID FOUND";
            }

        } catch (ObjectNotFoundEx ob) {
            String messageObError = ob.getMessage();
        }
        System.out.println(message);


    }

    @Override
    public CostumerEntity updateTask(CostumerEntity costumerEntity) {
        CostumerEntity costumerEntityDb = new CostumerEntity();
        costumerEntityDb.setIdCliente(costumerEntity.getIdCliente());
        costumerEntityDb.setRazonSocial(costumerEntity.getRazonSocial());
        return costumerRepositories.save(costumerEntityDb);
    }
}
