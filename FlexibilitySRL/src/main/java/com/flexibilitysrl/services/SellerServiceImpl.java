package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.SellerEntity;
import com.flexibilitysrl.exception.IllegalArgEx;
import com.flexibilitysrl.repositories.SellerRepositories;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SellerServiceImpl implements ISellerService {
    private static final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);
    @Autowired
    private SellerRepositories sellerRepositories;
    
    @Override
    public Iterable<SellerEntity> findAll() {
        return sellerRepositories.findAll();
    }

    @Override
    public SellerEntity save(SellerEntity sellerEntity) {
        return sellerRepositories.save(sellerEntity);
    }

    @Override
    public SellerEntity findBy(Long id) {
        return sellerRepositories.findById(id).orElseThrow(()-> new IllegalArgEx("SELLS ERROR"));

    }

    @Override
    public void deleteBy(Long id) {
        String message = null;
        try {
            if (id != null) {
                sellerRepositories.deleteById(id);
                message = "ID FOUND";
            }

        } catch (IllegalArgEx ob) {
            ob.getMessage();
        }
        logger.info(message);
    }

    @Override
    public SellerEntity updateTask(SellerEntity sellerEntity) {
       SellerEntity sellerEntityDb = new SellerEntity();
        sellerEntityDb.setIdVendedor(sellerEntity.getIdVendedor());
        sellerEntityDb.setCantVentas(sellerEntity.getCantVentas()+1);
        return sellerRepositories.save(sellerEntityDb);
    }
}
