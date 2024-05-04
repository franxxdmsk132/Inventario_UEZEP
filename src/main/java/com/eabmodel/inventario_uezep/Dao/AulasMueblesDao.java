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
import java.util.Map;

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
    public void asignarMueblesAAulaConCantidad(int idAula, Map<Integer, Integer> mueblesConCantidad) {
        for (Map.Entry<Integer, Integer> entry : mueblesConCantidad.entrySet()) {
            Integer idMueble = entry.getKey();
            Integer cantidad = entry.getValue();

            // Validar que la cantidad no sea negativa
            if (cantidad < 0) {
                cantidad = 0; // Establecer la cantidad como cero si es negativa
            }

            // Verificar si hay suficientes muebles disponibles para asignar al aula
            String sqlMueblesDisponibles = "SELECT cantidad FROM muebles WHERE id_mueble = ?";
            Integer cantidadDisponible = jdbcTemplate.queryForObject(sqlMueblesDisponibles, Integer.class, idMueble);

            if (cantidadDisponible >= cantidad) {
                // Verificar si ya existe una asignación para este aula y mueble
                String sqlExisteAsignacion = "SELECT COUNT(*) FROM aulas_muebles WHERE id_aula = ? AND id_mueble = ?";
                int count = jdbcTemplate.queryForObject(sqlExisteAsignacion, Integer.class, idAula, idMueble);

                if (count == 0) {
                    // Si no existe, realizar la asignación
                    String sqlAsignarMueble = "INSERT INTO aulas_muebles (id_aula, id_mueble, cantidad) VALUES (?, ?, ?)";
                    jdbcTemplate.update(sqlAsignarMueble, idAula, idMueble, cantidad);

                    // Actualizar la cantidad y asignación de muebles en la tabla 'muebles'
                    String sqlActualizarMuebles = "UPDATE muebles SET cantidad = cantidad - ?, asignacion = asignacion + ? WHERE id_mueble = ?";
                    jdbcTemplate.update(sqlActualizarMuebles, cantidad, cantidad, idMueble);
                }
            } else {
                // Manejar el caso en el que no haya suficientes muebles disponibles
                throw new IllegalArgumentException("No hay suficientes muebles disponibles para asignar al aula.");
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
    public List<AulasMuebles> findMueblesOfAulasById(int id_aula) {
        String sql = "    SELECT\n" +
                "    m.id_mueble,\n" +
                "    m.codigo_mueble,\n" +
                "    m.nombre_mueble,\n" +
                "    m.descripcion_mueble\n" +
                "            FROM\n" +
                "    aulas_muebles am\n" +
                "    INNER JOIN muebles m ON\n" +
                "    am.id_mueble = m.id_mueble\n" +
                "            WHERE\n" +
                "    am.id_aula = ?;";
        return jdbcTemplate.query(sql, new AulasMueblesRowMapper(), id_aula);
    }
    public AulasMuebles findAsignacionByIdAulaAndIdMueble(int id_aula, int id_mueble) {
        String sql = "SELECT * FROM aulas_muebles WHERE id_aula = ? AND id_mueble = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id_aula, id_mueble}, new AulasMueblesRowMapper());
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


//    SELECT
//    m.id_mueble,
//    m.codigo_mueble,
//    m.nombre_mueble,
//    m.descripcion_mueble
//            FROM
//    aulas_muebles am
//    INNER JOIN muebles m ON
//    am.id_mueble = m.id_mueble
//            WHERE
//    am.id_aula = 7;

}
