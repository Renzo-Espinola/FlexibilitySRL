package com.flexibilitysrl.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDAO {
    @ApiModelProperty(hidden = true)
    private String idProducto;
    private String nombreProducto;
    private double precio;
    private Long stock;
    private String tipo;
}