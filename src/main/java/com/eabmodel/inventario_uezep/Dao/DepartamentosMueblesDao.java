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
            String sqlExisteAsignacion = "SELECT COUNT(*) FROM departamentos_muebles WHERE id_departamentos = ? AND id_mueble = ?";
            int count = jdbcTemplate.queryForObject(sqlExisteAsignacion, Integer.class, idDepartamento, idMueble);
            if (count == 0) {
                String sqlAsignarMueble = "INSERT INTO departamentos_muebles (id_departamentos, id_mueble) VALUES (?, ?)";
                jdbcTemplate.update(sqlAsignarMueble, idDepartamento, idMueble);
            }
        }
    }

    public void actualizarAsignacionMueblesDepartamentos(int id_departamentos, int id_muebles) {
        String sqlExisteAsignacion = "SELECT COUNT(*) FROM departamentos_muebles WHERE id_departamentos = ? AND id_mueble = ?";
        int count = jdbcTemplate.queryForObject(sqlExisteAsignacion, Integer.class, id_departamentos, id_muebles);
        if (count == 0) {
            // Aquí puedes agregar la lógica para manejar el caso en el que no exista una asignación.
        }
    }

    public void eliminarAsignacionMueblesDepartamentos(int id_departamentos, int id_muebles) {
        String sqlEliminarAsignacion = "DELETE FROM departamentos_muebles WHERE id_departamentos = ? AND id_mueble = ?";
        jdbcTemplate.update(sqlEliminarAsignacion, id_departamentos, id_muebles);
    }

    public void eliminarAsignacionMueblesDepartamentosById(int id) {
        String sqlEliminarAsignacion = "DELETE FROM departamentos_muebles WHERE id = ?";
        jdbcTemplate.update(sqlEliminarAsignacion, id);
    }

    public List<DepartamentosMuebles> findAllWithDetails() {
        String sql = "SELECT dm.*, d.tipo_jornada AS tipo_jornada, m.nombre_mueble AS mueble_nombre " +
                "FROM departamentos_muebles dm " +
                "INNER JOIN departamentos d ON dm.id_departamentos = d.id_departamentos " +
                "INNER JOIN muebles m ON dm.id_mueble = m.id_mueble";
        return jdbcTemplate.query(sql, new DepartamentoMuebleRowMapper());
    }

    public List<DepartamentosMuebles> findAllDetails3() {
        String sql = "SELECT dm.*, d.*, m.* " +
                "FROM departamentos_muebles dm " +
                "JOIN departamentos d ON dm.id_departamentos = d.id_departamentos " +
                "JOIN muebles m ON dm.id_mueble = m.id_mueble " +
                "GROUP BY d.id_departamentos";
        return jdbcTemplate.query(sql, new DepartamentoMuebleRowMapper());
    }

    public List<DepartamentosMuebles> findAllDepartamentosMueblesWithDetailsGroupBy() {
        String sql = "SELECT dm.id, d.id_departamentos, m.id_mueble, GROUP_CONCAT(m.id_mueble) AS id_muebles " +
                "FROM departamentos_muebles dm " +
                "INNER JOIN departamentos d ON dm.id_departamentos = d.id_departamentos " +
                "INNER JOIN muebles m ON dm.id_mueble = m.id_mueble " +
                "GROUP BY d.id_departamentos";
        return jdbcTemplate.query(sql, new DepartamentoMuebleRowMapper());
    }

    public List<DepartamentosMuebles> findDetailsByDepartamentosId(int id_departamentos) {
        String sql = "SELECT dm.*, d.*, m.* " +
                "FROM departamentos_muebles dm " +
                "INNER JOIN departamentos d ON dm.id_departamentos = d.id_departamentos " +
                "INNER JOIN muebles m ON dm.id_mueble = m.id_mueble " +
                "WHERE d.id_departamentos = ?";
        return jdbcTemplate.query(sql, new DepartamentoMuebleRowMapper(), id_departamentos);
    }

    public List<DepartamentosMuebles> findMueblesOfDepartamentosById(int id_departamentos) {
        String sql = "SELECT m.id_mueble, m.codigo_mueble, m.nombre_mueble, m.descripcion_mueble " +
                "FROM departamentos_muebles dm " +
                "INNER JOIN muebles m ON dm.id_mueble = m.id_mueble " +
                "WHERE dm.id_departamentos = ?";
        return jdbcTemplate.query(sql, new DepartamentoMuebleRowMapper(), id_departamentos);
    }

    public DepartamentosMuebles findAsignacionByIdDepartamentosAndIdMueble(int id_departamentos, int id_mueble) {
        String sql = "SELECT * FROM departamentos_muebles WHERE id_departamentos = ? AND id_mueble = ?";
        return jdbcTemplate.queryForObject(sql, new DepartamentoMuebleRowMapper(), id_departamentos, id_mueble);
    }
}
