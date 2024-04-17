package com.eabmodel.inventario_uezep.Dao;

import com.eabmodel.inventario_uezep.Entity.Aulas;
import com.eabmodel.inventario_uezep.Entity.AulasMuebles;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.RowMapper.AulasRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AulasDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Aulas aulas) {
        String sql = "INSERT INTO aulas (curso, encargado, ubicacion_aula) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, aulas.getCurso(), aulas.getEncargado(), aulas.getUbicacion_aula());
    }

    public List<Aulas> findAll() {
        String sql = "SELECT * FROM aulas";
        return jdbcTemplate.query(sql, new AulasRowMapper());
    }

    public Aulas findByIdAula(int id_aula) {
        String sql = "SELECT * FROM aulas WHERE id_aula = ?";
        return jdbcTemplate.queryForObject(sql, new AulasRowMapper(), id_aula);
    }

    public void delete(int id_aula) {
        String sql = "DELETE FROM aulas WHERE id_aula = ?";
        jdbcTemplate.update(sql, id_aula);
    }

    public void update(Aulas aulas) {
        String sql = "UPDATE aulas SET curso = ?, encargado = ?, ubicacion_aula = ? WHERE id_aula = ?";
        jdbcTemplate.update(sql, aulas.getCurso(), aulas.getEncargado(), aulas.getUbicacion_aula(), aulas.getId_aula());
    }

    public void addMuebleToAula(int id_aula, int id_mueble) {
        Aulas aula = entityManager.find(Aulas.class, id_aula);
        Muebles mueble = entityManager.find(Muebles.class, id_mueble);

        if (aula != null && mueble != null) {
            AulasMuebles aulasMuebles = new AulasMuebles();
            aulasMuebles.setAulas(aula);
            aulasMuebles.setMuebles(mueble);
            entityManager.persist(aulasMuebles);
        } else {
            // Manejar el caso si el aula o el mueble no existen
            throw new RuntimeException("No se encontró, el aula o el mueble");
        }
    }

    public void removeMuebleFromAula(int id_aula, int id_mueble) {
        AulasMuebles aulasMuebles = entityManager.createQuery("SELECT am FROM AulasMuebles am WHERE am.aulas.id_aula = :id_aula AND am.muebles.id_mueble = :id_mueble", AulasMuebles.class)
                .setParameter("id_aula", id_aula)
                .setParameter("id_mueble", id_mueble)
                .getSingleResult();

        entityManager.remove(aulasMuebles);
    }

}
