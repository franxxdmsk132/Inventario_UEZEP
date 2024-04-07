package com.eabmodel.inventario_uezep.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "muebles")
public class Muebles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_mueble;
    private String codigoMueble;
    private String nombreMueble;
    private String descripcionMueble;
    private String fechaIngreso;
    private Boolean estado;
    private Integer cantidad;
    private String asignacion;


}
