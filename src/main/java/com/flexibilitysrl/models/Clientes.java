package com.flexibilitysrl.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clientes {
    private int idCliente;
    private Persona persona;
    private String razonSocial;
}
