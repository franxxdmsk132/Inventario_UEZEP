package com.eabmodel.inventario_uezep.Dao;

import com.eabmodel.inventario_uezep.Entity.Aulas;
import com.eabmodel.inventario_uezep.RowMapper.AulasRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AulasDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void save(Aulas aulas) {
        String sql = "INSERT INTO `aulas`(curso, encargado, ubicacion_aula, id_mueble) VALUES (?, ?, ?,?)";
        jdbcTemplate.update(
                sql,
                aulas.getCurso(),
                aulas.getEncargado(),
                aulas.getUbicacion_aula(),
                aulas.getMuebles().getId_mueble()
        );
    }

    public List<Aulas> findAll() {
        String sql = "SELECT * FROM `aulas` ";
        return jdbcTemplate.query(sql, new AulasRowMapper());
    }

    public List<Aulas> findAllWithAllDetails() {
        String sql = "SELECT a.*, m.* FROM aulas a JOIN muebles m ON a.id_mueble = m.id_mueble";
        return jdbcTemplate.query(sql, new AulasRowMapper());
    }

    public Aulas findByIdAula(int id_aula) {
        String sql = "SELECT * FROM aulas WHERE id_aula = ? ";
        return jdbcTemplate.queryForObject(sql, new AulasRowMapper(), id_aula);
    }
    public void delete(int id_aula) {
        String sql = "DELETE FROM aulas WHERE id_aula = " + id_aula;
        jdbcTemplate.update(sql);
    }



}
