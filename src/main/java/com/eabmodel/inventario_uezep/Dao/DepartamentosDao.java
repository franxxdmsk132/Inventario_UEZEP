package com.eabmodel.inventario_uezep.Dao;


import com.eabmodel.inventario_uezep.Entity.Departamentos;
import com.eabmodel.inventario_uezep.Entity.DepartamentosMuebles;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.RowMapper.DepartamentoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DepartamentosDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Departamentos departamentos) {
        String sql = "INSERT INTO departamentos (tipo_jornada, tipo_departamento, encargado_departamento) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, departamentos.getTipo_jornada(), departamentos.getTipo_departamento(), departamentos.getEncargado_departamento());
    }

    public List<Departamentos> findAll() {
        String sql = "SELECT * FROM departamentos";
        return jdbcTemplate.query(sql, new DepartamentoRowMapper());
    }

    public Departamentos findByIdDepartamentos(int id_departamento) {
        String sql = "SELECT * FROM departamentos WHERE id_departamento = ?";
        return jdbcTemplate.queryForObject(sql, new DepartamentoRowMapper(), id_departamento);
    }

    public void delete(int id_departamento) {
        String sql = "DELETE FROM departamentos WHERE id_departamento = ?";
        jdbcTemplate.update(sql, id_departamento);
    }

    public void update(Departamentos departamentos) {
        String sql = "UPDATE departamentos SET tipo_jornada = ?, tipo_departamento = ?, encargado_departamento = ? WHERE id_departamento = ?";
        jdbcTemplate.update(sql, departamentos.getTipo_jornada(), departamentos.getTipo_departamento(), departamentos.getEncargado_departamento(), departamentos.getId_departamento());
    }

    public void addMuebleToDepartamento(int id_departamento, int id_mueble) {
        Departamentos departamentos = entityManager.find(Departamentos.class, id_departamento);
        Muebles muebles = entityManager.find(Muebles.class, id_mueble);

        if (departamentos != null && muebles != null) {
            DepartamentosMuebles departamentosMuebles = new DepartamentosMuebles();
            departamentosMuebles.setDepartamento(departamentos);
            departamentosMuebles.setMueble(muebles);
            entityManager.persist(departamentosMuebles);
        } else {
            throw new RuntimeException("No se encontró el departamento o el mueble");
        }
    }

    public void removeMuebleFromDepartamento(int id_departamento, int id_mueble) {

            DepartamentosMuebles departamentosMuebles = entityManager.createQuery("SELECT dm FROM DepartamentosMuebles dm WHERE dm.departamentos.id_departamento = :id_departamento AND dm.muebles.id_mueble = :id_mueble", DepartamentosMuebles.class)
                    .setParameter("id_departamento", id_departamento)
                    .setParameter("id_mueble", id_mueble)
                    .getSingleResult();

            entityManager.remove(departamentosMuebles);

    }
}

