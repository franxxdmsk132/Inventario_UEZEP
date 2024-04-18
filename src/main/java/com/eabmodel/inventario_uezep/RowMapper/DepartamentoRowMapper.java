package com.eabmodel.inventario_uezep.RowMapper;

import com.eabmodel.inventario_uezep.Entity.Departamentos;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartamentoRowMapper implements RowMapper<Departamentos> {

    @Override
    public Departamentos mapRow(ResultSet rs, int rowNum) throws SQLException {
        Departamentos departamentos = new Departamentos();
        departamentos.setId_departamento(rs.getInt("id_departamento"));
        departamentos.setTipo_jornada(rs.getString("Tipo_jornada"));
        departamentos.setTipo_departamento(rs.getString("tipo_departamento"));
        departamentos.setEncargado_departamento(rs.getString("encargado_departamento"));

        return departamentos;
    }
}


