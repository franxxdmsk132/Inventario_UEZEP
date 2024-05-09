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
@Table(name = "muebles")
public class Muebles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_mueble;
    private String codigo_mueble;
    private String nombre_mueble;
    private String descripcion_mueble;
    private String fecha_ingreso;
    private Boolean estado;
    private Integer cantidad;
    private Integer asignacion;
    @OneToMany(mappedBy = "muebles")
    private List<AulasMuebles> aulas_muebles;
}
