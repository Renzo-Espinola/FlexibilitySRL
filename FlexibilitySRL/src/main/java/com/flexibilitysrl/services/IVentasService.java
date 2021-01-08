package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ClientesEntity;
import com.flexibilitysrl.entity.ProductoEntity;
import com.flexibilitysrl.entity.VendedorEntity;
import com.flexibilitysrl.entity.VentasEntity;
import com.flexibilitysrl.exception.ObjectNotFoundEx;

import java.util.List;

public interface IVentasService {
    VentasEntity saveVentas (VentasEntity ventasEntity,
                             List<ProductoEntity> productoEntity,
                             ClientesEntity clientesEntity,
                             VendedorEntity vendedorEntity)throws ObjectNotFoundEx;
    void deleteVentas(Long id);
    VentasEntity updateVentas (VentasEntity ventasEntity);
    Iterable<VentasEntity>findAllVentas();
    VentasEntity findByIdVenta(Long id);

}
