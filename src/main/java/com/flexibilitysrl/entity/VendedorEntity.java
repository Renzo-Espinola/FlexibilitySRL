package com.flexibilitysrl.entity;

import com.flexibilitysrl.models.Persona;
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
    @Column
    private Long idVendedor;
    @Column
    private Long cantVentas;

}
