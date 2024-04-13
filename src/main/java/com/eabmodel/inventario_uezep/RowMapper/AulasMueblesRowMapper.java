package com.eabmodel.inventario_uezep.RowMapper;

import com.eabmodel.inventario_uezep.Entity.Aulas;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.Entity.AulasMuebles;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AulasMueblesRowMapper implements RowMapper<AulasMuebles> {

    @Override
    public AulasMuebles mapRow(ResultSet rs, int rowNum) throws SQLException {
        AulasMuebles aulasMuebles = new AulasMuebles();

        // Mapeo de los campos de la tabla intermedia a los campos de la entidad AulasMuebles
        aulasMuebles.setId(rs.getInt("id"));

        // Mapeo de la relación con la entidad Aulas
        Aulas aulas = new Aulas();
        aulas.setId_aula(rs.getInt("id_aula")); // Suponiendo que el campo en la tabla intermedia es "id_aula"
        aulasMuebles.setAulas(aulas);

        // Mapeo de la relación con la entidad Muebles
        Muebles muebles = new Muebles();
        muebles.setId_mueble(rs.getInt("id_mueble")); // Suponiendo que el campo en la tabla intermedia es "id_mueble"
        aulasMuebles.setMuebles(muebles);

        return aulasMuebles;
    }
}
