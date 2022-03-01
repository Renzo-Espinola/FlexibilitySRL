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
@Table(name="Sells")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesEntity {
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
    private List<ProductEntity> productoEntities;
    @OneToOne
    @ApiModelProperty(hidden = true)
    private CostumerEntity costumerEntity;
    @OneToOne
    @ApiModelProperty(hidden = true)
    private SellerEntity sellerEntity;
    @PrePersist
    public void prePersist(){this.fechaCompra=new Date();}

}
