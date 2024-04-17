package com.eabmodel.inventario_uezep.Dao;

import com.eabmodel.inventario_uezep.Entity.DepartamentosMuebles;
import com.eabmodel.inventario_uezep.RowMapper.DepartamentoMuebleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DepartamentosMueblesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void asignarMuebles(int idDepartamento, List<Integer> idMuebles) {
        for (Integer idMueble : idMuebles) {
            String sqlExisteAsignacion = "SELECT COUNT(*) FROM departamento_muebles WHERE id_departamentos = ? AND id_mueble = ?";
            int count = jdbcTemplate.queryForObject(sqlExisteAsignacion, Integer.class, idDepartamento, idMueble);
            if (count == 0) {
                String sqlAsignarMueble = "INSERT INTO departamentos_muebles (id_departamentos, id_mueble) VALUES (?, ?)";
                jdbcTemplate.update(sqlAsignarMueble, idDepartamento, idMueble);
            }
        }
    }

    public List<DepartamentosMuebles> findAllWithDetails() {
        String sql = "SELECT dm.*, d.tipo_jornada AS tipo_jornada, m.nombre_mueble AS mueble_nombre FROM departamentos_muebles dm " +
                "INNER JOIN departamentos d ON dm.id_departamentos = d.id_departamentos " +
                "INNER JOIN muebles m ON dm.id_mueble = m.id_mueble";
        return jdbcTemplate.query(sql, new DepartamentoMuebleRowMapper());
    }
}
