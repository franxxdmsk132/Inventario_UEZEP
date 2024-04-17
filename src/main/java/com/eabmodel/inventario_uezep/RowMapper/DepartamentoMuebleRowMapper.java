package com.eabmodel.inventario_uezep.RowMapper;

import com.eabmodel.inventario_uezep.Entity.Departamentos;
import com.eabmodel.inventario_uezep.Entity.DepartamentosMuebles;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartamentoMuebleRowMapper implements RowMapper<DepartamentosMuebles> {

    @Override
    public  DepartamentosMuebles mapRow (ResultSet rs, int rowNum) throws SQLException{
        DepartamentosMuebles departamentosMuebles = new DepartamentosMuebles();

        departamentosMuebles.setId(rs.getInt("id"));

        Departamentos departamentos = new Departamentos();
        departamentos.setId_departamento(rs.getInt("id_departamentos"));
        departamentosMuebles.setDepartamentos(departamentos);

        Muebles muebles = new Muebles();
        muebles.setId_mueble(rs.getInt("id_mueble"));
        departamentosMuebles.setMuebles(muebles);

        return departamentosMuebles;
    }
}
