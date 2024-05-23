package com.eabmodel.inventario_uezep.RowMapper;

import com.eabmodel.inventario_uezep.Entity.Muebles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MueblesRowMapper implements RowMapper <Muebles>{
    @Override
    public Muebles mapRow(ResultSet rs, int rowNum) throws SQLException {
        Muebles muebles = new Muebles();
        muebles.setId_mueble(rs.getInt("id_mueble"));
        muebles.setCodigo_mueble(rs.getString("codigo_mueble"));
        muebles.setNombre_mueble(rs.getString("nombre_mueble"));
        muebles.setDescripcion_mueble(rs.getString("descripcion_mueble"));
        muebles.setFecha_ingreso(rs.getString("fecha_ingreso"));
        muebles.setEstado(rs.getBoolean("estado"));
        muebles.setCondicion(rs.getString("condicion"));
        muebles.setCantidad(rs.getInt("cantidad"));
        muebles.setAsignacion(rs.getInt("asignacion"));
        return muebles;
    }
}
