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
public class Costumer extends PersonEntity {
    private Long idCliente;
    private String razonSocial;
    public Costumer(Long idCliente, Long dni, String nombre, String apellido,  String razonSocial) {
        super(dni, nombre, apellido);
        this.idCliente = idCliente;
        this.razonSocial = razonSocial;
    }
}
