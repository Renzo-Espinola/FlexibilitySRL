package com.flexibilitysrl.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Productos {

private Long idProducto;
private String nombProd;
private double precio;
private int stock;
}
