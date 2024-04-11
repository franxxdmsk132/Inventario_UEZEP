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
        muebles.setCodigoMueble(rs.getString("codigo_mueble"));
        muebles.setNombreMueble(rs.getString("nombre_mueble"));
        muebles.setDescripcionMueble(rs.getString("descripcion_mueble"));
        muebles.setFechaIngreso(rs.getString("fecha_ingreso"));
        muebles.setEstado(rs.getBoolean("estado"));
        muebles.setCantidad(rs.getInt("cantidad"));
        muebles.setAsignacion(rs.getInt("asignacion"));
        return muebles;
    }
}
