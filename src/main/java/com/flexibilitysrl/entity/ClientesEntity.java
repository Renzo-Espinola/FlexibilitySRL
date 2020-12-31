package com.flexibilitysrl.entity;

import com.flexibilitysrl.models.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientesEntity extends PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false)
    private Long idCliente;
    @Column
    private String razonSocial;
}
