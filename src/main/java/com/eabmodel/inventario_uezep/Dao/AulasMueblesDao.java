package com.eabmodel.inventario_uezep.Dao;

import com.eabmodel.inventario_uezep.Entity.Aulas;
import com.eabmodel.inventario_uezep.Entity.AulasMuebles;
import com.eabmodel.inventario_uezep.RowMapper.AulasMueblesRowMapper;
import com.eabmodel.inventario_uezep.RowMapper.AulasRowMapper;
import com.eabmodel.inventario_uezep.RowMapper.MueblesRowMapper;
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
    public List<AulasMuebles> findAllWithDetails() {
        String sql = "SELECT am.*, a.curso AS aula_curso, m.nombre_mueble AS mueble_nombre FROM aulas_muebles am " +
                "INNER JOIN aulas a ON am.id_aula = a.id_aula " +
                "INNER JOIN muebles m ON am.id_mueble = m.id_mueble";
        return jdbcTemplate.query(sql, new AulasMueblesRowMapper());
    }
        public List<AulasMuebles> findAllWithDetails3() {
            String sql = "SELECT \n" +
                    "\t*,\n" +
                    "    a.*,\n" +
                    "    m.*\n" +
                    "FROM\n" +
                    "    aulas_muebles am\n" +
                    "JOIN aulas a ON\n" +
                    "    am.id_aula = a.id_aula\n" +
                    "JOIN muebles m ON\n" +
                    "    am.id_mueble = m.id_mueble\n" +
                    "GROUP BY\n" +
                    "    a.id_aula;\n";
            return jdbcTemplate.query(sql, new AulasMueblesRowMapper());
        }
    public List<AulasMuebles> findAllAulasMueblesWithDetailsGroupBy() {
        String sql = "SELECT am.id, a.id_aula, m.id_mueble, GROUP_CONCAT(m.id_mueble) AS id_muebles " +
                "FROM aulas_muebles am " +
                "INNER JOIN aulas a ON am.id_aula = a.id_aula " +
                "INNER JOIN muebles m ON am.id_mueble = m.id_mueble " +
                "GROUP BY a.id_aula";
        return jdbcTemplate.query(sql, new AulasMueblesRowMapper());
    }
    public List<AulasMuebles> findDetailsByAulaId(int id_aula) {
        String sql = "SELECT\n" +
                "    am.*,\n" +
                "    a.*,\n" +
                "    m.*\n" +
                "FROM\n" +
                "    aulas_muebles am\n" +
                "INNER JOIN aulas a ON\n" +
                "    am.id_aula = a.id_aula\n" +
                "INNER JOIN muebles m ON\n" +
                "    am.id_mueble = m.id_mueble\n" +
                "WHERE\n" +
                "    a.id_aula = ?";
        return jdbcTemplate.query(sql, new AulasMueblesRowMapper(), id_aula);
    }





//    SELECT
//    a.id_aula,
//    a.curso,
//    GROUP_CONCAT(m.id_mueble) AS id_muebles
//    FROM
//    aulas_muebles am
//    INNER JOIN aulas a ON
//    am.id_aula = a.id_aula
//    INNER JOIN muebles m ON
//    am.id_mueble = m.id_mueble
//    GROUP BY
//    a.id_aula;

}
