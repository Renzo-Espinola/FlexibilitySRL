package com.flexibilitysrl.repositories;

import com.flexibilitysrl.entity.ProductoEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepo extends CrudRepository<ProductoEntity,Long> {
}
