package com.flexibilitysrl.services;

import com.flexibilitysrl.entity.*;
import com.flexibilitysrl.exceptions.ObjectNotFoundEx;
import com.flexibilitysrl.repositories.ClienteRepo;
import com.flexibilitysrl.repositories.ProductoRepo;
import com.flexibilitysrl.repositories.VendedorRepo;
import com.flexibilitysrl.repositories.VentasRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
@Slf4j
public class VentasServiceImpl implements IVentasService {
    @Autowired
    private VentasRepo ventasRepo;
    @Autowired
    private ClienteRepo clienteRepo;
    @Autowired
    private VendedorRepo vendedorRepo;
    @Autowired
    private ProductoRepo productoRepo;

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private ClientesEntity clienteDefault = new ClientesEntity(1L, "RS_DEFAULT");
    private VendedorEntity vendedorDefault = new VendedorEntity(1L, 1L);
    private PersonaEntity personaDefault = new PersonaEntity(1L, "NOM_DEFAULT", "AP_DEFAULT");
    private ProductoEntity productoDefault = new ProductoEntity(1L, "P_DEFAULT", 50, 5, "T_DEFAULT");
    private static Logger logger = LoggerFactory.getLogger(VentasServiceImpl.class);

    @Override
    public VentasEntity saveVentas(VentasEntity ventasEntity,Optional<ProductoEntity> productoEntity, Optional<ClientesEntity> clientesEntity, Optional<VendedorEntity> vendedorEntity) {
        String message = "";
        double totalCompra = 0;
        int i = 0;
        ClientesEntity clientesEntity1;
        ProductoEntity productoEntity1;
        VendedorEntity vendedorEntity1;
        VentasEntity ventasEntityDb = new VentasEntity();
        if (clientesEntity.isPresent()) {
            Optional<ClientesEntity> optClientesEntity = clienteRepo.findById(clientesEntity.get().getIdCliente());
            clientesEntity1 = optClientesEntity.get();
            ventasEntityDb.setClientesEntity(clientesEntity1);
        } else {
            ventasEntityDb.setClientesEntity(clienteDefault);
        }
        if (productoEntity.isPresent()) {
            Optional<ProductoEntity> optProductoEntity = productoRepo.findById(productoEntity.get().getIdProducto());
            productoEntity1 = optProductoEntity.get();
            ventasEntityDb.setProductoEntities(productoEntity1);
            totalCompra=ventasEntityDb.getProductoEntities().getPrecio()+totalCompra;
        } else {
            ventasEntityDb.setProductoEntities(productoDefault);
        }
        if (vendedorEntity.isPresent()) {
            Optional<VendedorEntity> optVendedorEntity = vendedorRepo.findById(vendedorEntity.get().getIdVendedor());
            vendedorEntity1 = optVendedorEntity.get();
            ventasEntityDb.setVendedorEntity(vendedorEntity1);

        } else {
            ventasEntityDb.setVendedorEntity(vendedorDefault);
        }
        ventasEntityDb.setIdVentas(ventasEntity.getIdVentas());
        ventasEntityDb.setCantidad(ventasEntity.getCantidad());
        ventasEntityDb.setFechaCompra(ventasEntity.getFechaCompra());
        ventasEntityDb.setTotal(totalCompra*ventasEntityDb.getCantidad());
        return ventasEntityDb;
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
        return ventasRepo.findAll();
    }

    @Override
    public Optional<VentasEntity> findByIdVenta(Long id) {
        Optional<VentasEntity> message = null;
        try {
            if (id != null) {
                message = ventasRepo.findById(id);

            }
        } catch (ObjectNotFoundEx ob) {
            logger.error(ob.getMessage());
        }
        return message;
    }
}
