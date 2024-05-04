package com.eabmodel.inventario_uezep.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aulas_muebles")
public class AulasMuebles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cantidad;


    @ManyToOne
    @JoinColumn(name = "id_aula")
    private Aulas aulas;

    @ManyToOne
    @JoinColumn(name = "id_mueble")
    private Muebles muebles;
}