package com.eabmodel.inventario_uezep.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departamento_mueble")
public class DepartamentosMuebles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamentos departamento;

    @ManyToOne
    @JoinColumn(name = "id_mueble")
    private Muebles mueble;
}
