package com.eabmodel.inventario_uezep.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AulasMueblesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void asignarMueblesAAula(int idAula, List<Integer> idMuebles) {
        for (Integer idMueble : idMuebles) {
            // Verificar si ya existe una asignación para este aula y mueble
            String sqlExisteAsignacion = "SELECT COUNT(*) FROM aulas_muebles WHERE id_aula = ? AND id_mueble = ?";
            int count = jdbcTemplate.queryForObject(sqlExisteAsignacion, Integer.class, idAula, idMueble);

            if (count == 0) {
                // Si no existe, realizar la asignación
                String sqlAsignarMueble = "INSERT INTO aulas_muebles (id_aula, id_mueble) VALUES (?, ?)";
                jdbcTemplate.update(sqlAsignarMueble, idAula, idMueble);
            }
        }
    }
}
