package com.flexibilitysrl.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class PersonaEntity {
    @Column
    private Long dni;
    @Column
    private String nombre;
    @Column
    private String apellido;
}
