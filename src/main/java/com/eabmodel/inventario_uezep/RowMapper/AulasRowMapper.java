package com.eabmodel.inventario_uezep.RowMapper;

import com.eabmodel.inventario_uezep.Entity.Aulas;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AulasRowMapper implements RowMapper<Aulas> {

    @Override
    public Aulas mapRow(ResultSet rs, int rowNum) throws SQLException {
        Aulas aulas = new Aulas();
        aulas.setId_aula(rs.getInt("id_aula"));
        aulas.setCurso(rs.getString("curso"));
        aulas.setParalelo(rs.getString("paralelo"));
        aulas.setUbicacion_aula(rs.getString("ubicacion_aula"));
        aulas.setEncargado(rs.getString("encargado"));
        return aulas;
    }

}