package com.flexibilitysrl.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {
    private int idVendedor;
    private Persona persona;
    private int CantVentas;
}
