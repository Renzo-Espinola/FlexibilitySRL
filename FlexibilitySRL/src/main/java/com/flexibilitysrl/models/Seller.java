package com.flexibilitysrl.models;

import com.flexibilitysrl.entity.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends PersonEntity {

    private Long idVendedor;
    private Long cantVentas;
    public Seller(Long idVendedor, Long dni, String nombre, String apellido,Long cantVentas) {
        super(dni, nombre, apellido);
        this.idVendedor = idVendedor;
        this.cantVentas = cantVentas;
    }
}
