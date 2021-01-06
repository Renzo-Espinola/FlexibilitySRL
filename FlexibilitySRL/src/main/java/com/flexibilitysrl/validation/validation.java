package com.flexibilitysrl.validation;

import com.flexibilitysrl.entity.VentasEntity;
import com.flexibilitysrl.repositories.ClienteRepo;
import com.flexibilitysrl.repositories.ProductoRepo;
import com.flexibilitysrl.repositories.VendedorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class validation {
    private static Logger logger = LoggerFactory.getLogger(validation.class);

    private ProductoRepo productoRepo;
    private ClienteRepo clienteRepo;
    private VendedorRepo vendedorRepo;

    public validation(ProductoRepo productoRepo, ClienteRepo clienteRepo, VendedorRepo vendedorRepo){
        this.clienteRepo = clienteRepo;
        this.productoRepo = productoRepo;
        this.vendedorRepo = vendedorRepo;
        this.validVenta = new VentasEntity();
    }
    VentasEntity validVenta;
}
