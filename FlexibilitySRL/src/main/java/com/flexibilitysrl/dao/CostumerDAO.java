package com.flexibilitysrl.dao;

import com.flexibilitysrl.entity.PersonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CostumerDAO extends PersonEntity {
    @ApiModelProperty(hidden = true)
    private Long idCliente;
    private String razonSocial;

    public CostumerDAO(Long idCliente, Long dni, String nombre, String apellido, String razonSocial) {
        super(dni, nombre, apellido);
        this.idCliente = idCliente;
        this.razonSocial = razonSocial;
    }
}
