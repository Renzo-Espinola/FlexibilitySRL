package com.flexibilitysrl.dao;

import com.flexibilitysrl.entity.PersonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerDAO extends PersonEntity {

    @ApiModelProperty(hidden = true)
    private Long idVendedor;
    @ApiModelProperty(hidden = true)
    private Long cantVentas=0L;

    public SellerDAO(Long idVendedor, Long dni, String nombre, String apellido,Long cantVentas) {
        super(dni, nombre, apellido);
        this.idVendedor = idVendedor;
        this.cantVentas = cantVentas;
    }
}
