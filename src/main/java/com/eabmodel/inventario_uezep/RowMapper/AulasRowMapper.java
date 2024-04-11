package com.eabmodel.inventario_uezep.RowMapper;

import com.eabmodel.inventario_uezep.Entity.Aulas;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AulasRowMapper implements RowMapper <Aulas> {

    @Override
    public Aulas mapRow(ResultSet rs, int rowNum) throws SQLException {
        Aulas aulas = new Aulas();
        aulas.setId_aula(rs.getInt("id_aula"));
        aulas.setCurso(rs.getString("curso"));
        aulas.setEncargado(rs.getString("encargado"));
        aulas.setUbicacion_aula(rs.getString("ubicacion_aula"));
        Muebles muebles = createMuebleFromResultSet(rs);
        aulas.setMuebles(muebles);
        return aulas;

    }
    private Muebles createMuebleFromResultSet(ResultSet rs) throws SQLException{
        Muebles muebles = new Muebles();
//        muebles.setId_mueble(rs.getInt("id_mueble"));
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
