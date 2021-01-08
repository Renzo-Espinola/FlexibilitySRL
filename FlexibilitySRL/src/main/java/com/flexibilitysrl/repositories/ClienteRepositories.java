package com.flexibilitysrl.repositories;

import com.flexibilitysrl.entity.ClientesEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepositories extends CrudRepository<ClientesEntity,Long> {
}
