package com.eabmodel.inventario_uezep.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departamentos_muebles")
public class DepartamentosMuebles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamentos departamentos;

    @ManyToOne
    @JoinColumn(name = "id_mueble")
    private Muebles muebles;
}
