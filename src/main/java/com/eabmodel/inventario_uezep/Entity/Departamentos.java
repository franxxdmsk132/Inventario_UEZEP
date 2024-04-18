package com.eabmodel.inventario_uezep.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private String encargado_departamento;

    @OneToMany(mappedBy = "departamentos")
    private List <DepartamentosMuebles> departamentosMuebles;








}
