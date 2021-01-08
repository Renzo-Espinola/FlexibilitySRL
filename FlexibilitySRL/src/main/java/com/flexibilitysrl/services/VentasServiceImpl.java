package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.ClientesEntity;
import com.flexibilitysrl.entity.ProductoEntity;
import com.flexibilitysrl.entity.VendedorEntity;
import com.flexibilitysrl.entity.VentasEntity;
import com.flexibilitysrl.exception.ObjectNotFoundEx;
import com.flexibilitysrl.repositories.ClienteRepositories;
import com.flexibilitysrl.repositories.ProductoRepositories;
import com.flexibilitysrl.repositories.VendedorRepositories;
import com.flexibilitysrl.repositories.VentasRepositories;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VentasServiceImpl implements IVentasService {
    @Autowired
    private VentasRepositories ventasRepositories;
    @Autowired
    private ClienteRepositories clienteRepositories;
    @Autowired
    private VendedorRepositories vendedorRepositories;
    @Autowired
    private ProductoRepositories productoRepositories;

    private static Logger logger = LoggerFactory.getLogger(VentasServiceImpl.class);

    @Override
    public VentasEntity saveVentas(VentasEntity ventasEntity,
                                   List<ProductoEntity> productoEntityOpt,
                                   ClientesEntity clientesEntityOpt,
                                   VendedorEntity vendedorEntityOpt) throws ObjectNotFoundEx {
        String message = "";
        double totalCompra = 0;
        Long i = 0L;
        VentasEntity ventasEntityDb = new VentasEntity();
        ventasEntityDb.setVendedorEntity(vendedorEntityOpt);
        ventasEntityDb.setClientesEntity(clientesEntityOpt);
        if (productoEntityOpt.stream().findFirst().get().getStock() > 0 &&
                ventasEntity.getCantidad() < productoEntityOpt.stream().findFirst().get().getStock()) {
            ventasEntityDb.setProductoEntities(productoEntityOpt);
        } else {
            throw new ObjectNotFoundEx("STOCK INSUFICIENTE");
        }
        totalCompra = ventasEntityDb.getProductoEntities().stream().findAny().get().getPrecio();
        ventasEntityDb.setIdVentas(ventasEntity.getIdVentas());
        ventasEntityDb.setCantidad(ventasEntity.getCantidad());
        ventasEntityDb.setTotal(totalCompra * ventasEntityDb.getCantidad());

        return ventasRepositories.save(ventasEntityDb);
    }

    @Override
    public void deleteVentas(Long id) {

    }

    @Override
    public VentasEntity updateVentas(VentasEntity ventasEntity) {
        return null;
    }

    @Override
    public Iterable<VentasEntity> findAllVentas() {
        return ventasRepositories.findAll();
    }

    @Override
    public VentasEntity findByIdVenta(Long id) {
        return ventasRepositories.findById(id).orElseThrow(() -> new ObjectNotFoundEx("ERROR"));
    }
}
//totalCompra = ventasEntityDb.getProductoEntities().get(i).getPrecio() + totalCompra;
//productoEntityOpt.stream().forEach((p) -> {
                    /*ProductoEntity producResg = new ProductoEntity();
                    producResg.setIdProducto(p.getIdProducto());
                    producResg.setNombreProducto(p.getNombreProducto());
                    producResg.setTipo(p.getTipo());
                    producResg.setPrecio(p.getPrecio());
                    producResg.setStock(p.getStock());*/
