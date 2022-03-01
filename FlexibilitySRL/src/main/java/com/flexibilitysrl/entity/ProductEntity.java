package com.flexibilitysrl.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    @Column
    private String idProducto;
    @Column
    private String nombreProducto;
    @Column
    private double precio;
    @Column
    private Long stock;
    @Column
    private String tipo;



}
