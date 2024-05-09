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
        aulasMuebles.setId(rs.getInt("id"));
        aulasMuebles.setCantidad(rs.getInt("cantidad"));


        // Mapeo de la relación con la entidad Aulas
        Aulas aulas = mapAulas(rs);
        aulasMuebles.setAulas(aulas);

        // Mapeo de la relación con la entidad Muebles
        Muebles muebles = mapMuebles(rs);
        aulasMuebles.setMuebles(muebles);

        return aulasMuebles;
    }

    private Aulas mapAulas(ResultSet rs) throws SQLException {
        Aulas aulas = new Aulas();
        aulas.setId_aula(rs.getInt("id_aula"));
        aulas.setCurso(rs.getString("curso"));
        aulas.setParalelo(rs.getString("paralelo"));
        aulas.setUbicacion_aula(rs.getString("ubicacion_aula"));
        aulas.setEncargado(rs.getString("encargado"));
        return aulas;
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
