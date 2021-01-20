package com.flexibilitysrl.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Costumer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CostumerEntity extends PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long idCliente;
    @Column
    private String razonSocial;

    public CostumerEntity(Long idCliente, Long dni, String nombre, String apellido,  String razonSocial) {
        super(dni, nombre, apellido);
        this.idCliente = idCliente;
        this.razonSocial = razonSocial;
    }
}
