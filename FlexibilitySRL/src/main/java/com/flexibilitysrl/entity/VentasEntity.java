package com.flexibilitysrl.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Ventas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VentasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    @Column
    private Long idVentas;
    @Column
    @ApiModelProperty(hidden = true)
    private double total;
    @Column
    private Long cantidad;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    @OneToMany
    @ApiModelProperty(hidden = true)
    @JoinColumn
    private List<ProductoEntity> productoEntities;
    @OneToOne
    @ApiModelProperty(hidden = true)
    private ClientesEntity clientesEntity;
    @OneToOne
    @ApiModelProperty(hidden = true)
    private VendedorEntity vendedorEntity;
    @PrePersist
    public void prePersist(){this.fechaCompra=new Date();}

}
