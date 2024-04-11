package com.eabmodel.inventario_uezep.Dao;


import com.eabmodel.inventario_uezep.Entity.Aulas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class AulasDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void save (Aulas aulas){
        String sql = "INSERT INTO `aulas`(tipo_jornada, tipo_departamento, encargado_departamento) VALUES (?, ?, ?)";

    }

}
