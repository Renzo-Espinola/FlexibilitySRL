package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ClientesEntity;
import com.flexibilitysrl.entity.ProductoEntity;
import com.flexibilitysrl.entity.VendedorEntity;
import com.flexibilitysrl.entity.VentasEntity;

import java.util.Optional;

public interface IVentasService {
    VentasEntity saveVentas (VentasEntity ventasEntity,Optional<ProductoEntity> productoEntity, Optional<ClientesEntity> clientesEntity, Optional<VendedorEntity> vendedorEntity);
    void deleteVentas(Long id);
    VentasEntity updateVentas (VentasEntity ventasEntity);
    Iterable<VentasEntity>findAllVentas();
    Optional<VentasEntity> findByIdVenta(Long id);

}
