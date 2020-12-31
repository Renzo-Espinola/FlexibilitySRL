package com.flexibilitysrl.repositories;

import com.flexibilitysrl.entity.ClientesEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepo extends CrudRepository<ClientesEntity,Long> {
}
