package com.eabmodel.inventario_uezep.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "departamentos")
public class Departamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id_departamento;
    private String tipo_jornada;
    private String tipo_departamento;
    private String encargado;

    @ManyToOne
    @JoinColumn(name = "id_mueble")
    private Muebles muebles;








}
