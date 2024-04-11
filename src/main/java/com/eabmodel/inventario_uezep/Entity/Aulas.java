package com.eabmodel.inventario_uezep.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "aulas")
public class Aulas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id_aula;
    private String curso;
    private String encargado;
    private String ubicacion_aula;

    @ManyToOne
    @JoinColumn(name = "id_mueble")
    private Muebles muebles;
}
