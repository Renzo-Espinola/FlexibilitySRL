package com.flexibilitysrl.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String idProducto;
    private String nombreProducto;
    private double precio;
    private Long stock;
    private String tipo;
}
