package com.eabmodel.inventario_uezep.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.renderers.SimpleRenderToImageAwareDataRenderer;

import javax.persistence.*;
import java.util.List;

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
    private String paralelo;
    private String encargado;
    private String ubicacion_aula;


    @OneToMany(mappedBy = "aulas")
    private List<AulasMuebles> aulasMuebles;
}
