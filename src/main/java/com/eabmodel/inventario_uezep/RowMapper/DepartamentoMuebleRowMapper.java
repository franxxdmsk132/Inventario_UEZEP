package com.eabmodel.inventario_uezep.RowMapper;

import com.eabmodel.inventario_uezep.Entity.Departamentos;
import com.eabmodel.inventario_uezep.Entity.DepartamentosMuebles;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartamentoMuebleRowMapper implements RowMapper<DepartamentosMuebles> {

    @Override
    public DepartamentosMuebles mapRow(ResultSet rs, int rowNum) throws SQLException {
        DepartamentosMuebles departamentosMuebles = new DepartamentosMuebles();
        departamentosMuebles.setId(rs.getInt("id"));
        departamentosMuebles.setCantidad(rs.getInt("cantidad"));

        Departamentos departamentos = mapDepartamentos(rs);
        departamentosMuebles.setDepartamentos(departamentos);

        Muebles muebles = mapMuebles(rs);
        departamentosMuebles.setMuebles(muebles);

        return departamentosMuebles;
    }

    private Departamentos mapDepartamentos(ResultSet rs) throws SQLException {
        Departamentos departamentos = new Departamentos();
        departamentos.setId_departamento(rs.getInt("id_departamento"));
        departamentos.setTipo_jornada(rs.getString("tipo_jornada"));
        departamentos.setTipo_departamento(rs.getString("tipo_departamento"));
        departamentos.setEncargado_departamento(rs.getString("encargado_departamento"));
        return departamentos;
    }

    private Muebles mapMuebles(ResultSet rs) throws SQLException {
        Muebles muebles = new Muebles();
        muebles.setId_mueble(rs.getInt("id_mueble"));
        muebles.setCodigo_mueble(rs.getString("codigo_mueble"));
        muebles.setNombre_mueble(rs.getString("nombre_mueble"));
        muebles.setDescripcion_mueble(rs.getString("descripcion_mueble"));
        muebles.setFecha_ingreso(rs.getString("fecha_ingreso"));
        muebles.setEstado(rs.getBoolean("estado"));
        muebles.setCantidad(rs.getInt("cantidad"));
        muebles.setAsignacion(rs.getInt("asignacion"));
        return muebles;
    }
}
