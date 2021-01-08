package com.flexibilitysrl.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Vendedor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendedorEntity extends PersonaEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    @Column
    private Long idVendedor;
    @Column
    @ApiModelProperty(hidden = true)
    private Long cantVentas=0L;

}
