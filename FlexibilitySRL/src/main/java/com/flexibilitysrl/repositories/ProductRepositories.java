package com.flexibilitysrl.repositories;

import com.flexibilitysrl.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepositories extends CrudRepository<ProductEntity,String> {
}
