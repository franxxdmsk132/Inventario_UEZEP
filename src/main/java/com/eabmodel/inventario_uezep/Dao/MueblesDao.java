package com.eabmodel.inventario_uezep.Dao;

import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.RowMapper.MueblesRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MueblesDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Muebles muebles) {
        String sql = "INSERT INTO `muebles`(codigo_mueble, nombre_mueble, descripcion_mueble, fecha_ingreso, estado, cantidad, asignacion)) VALUES ( ?, ?, ?, ?, ?, ?,?)";

        jdbcTemplate.update(
                sql,
                muebles.getCodigoMueble(),
                muebles.getNombreMueble(),
                muebles.getDescripcionMueble(),
                muebles.getFechaIngreso(),
                muebles.getEstado(),
                muebles.getCantidad(),
                muebles.getAsignacion()
        );
    }

    public List<Muebles> findAll() {
        String sql = "SELECT * FROM `muebles` ";
        return jdbcTemplate.query(sql, new MueblesRowMapper());
    }

    public Muebles findByIdMueble(int id_mueble) {
        String sql = "SELECT * FROM muebles WHERE id_mueble = ? ";
        return jdbcTemplate.queryForObject(sql, new MueblesRowMapper(), id_mueble);
    }

    public void update(Muebles muebles) {
        String sql = "UPDATE `muebles` SET `asignacion` = '?', `cantidad` = '?', `codigo_mueble` = '?', `estado` = '?', `fecha_ingreso` = '?', `nombre_mueble` = '?', `descripcion_mueble` = '?' WHERE `muebles`.`id_mueble` = ? ";
        jdbcTemplate.update(sql, muebles.getAsignacion(), muebles.getCantidad(), muebles.getCodigoMueble(), muebles.getEstado(), muebles.getFechaIngreso(), muebles.getNombreMueble(), muebles.getDescripcionMueble(), muebles.getId_mueble());
    }

//    public void updateEstado(Muebles muebles) {
//        String sql = "UPDATE `muebles` SET `estado` = ? WHERE `muebles`.`id_mueble` = ?";
//        jdbcTemplate.update(sql, muebles.getEstado(), muebles.getId_mueble());
//    }
    public void delete(int id_mueble) {
        String sql = "DELETE FROM muebles WHERE id_mueble = " + id_mueble;
        jdbcTemplate.update(sql);
    }



}
