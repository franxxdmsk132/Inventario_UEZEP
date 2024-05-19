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
                } else {
                    // Si ya existe, actualizar la cantidad asignada
                    String sqlActualizarAsignacion = "UPDATE aulas_muebles SET cantidad = cantidad + ? WHERE id_aula = ? AND id_mueble = ?";
                    jdbcTemplate.update(sqlActualizarAsignacion, cantidad, idAula, idMueble);
                }

                // Actualizar la cantidad y asignación de muebles en la tabla 'muebles'
                String sqlActualizarMuebles = "UPDATE muebles SET cantidad = cantidad - ?, asignacion = asignacion + ? WHERE id_mueble = ?";
                jdbcTemplate.update(sqlActualizarMuebles, cantidad, cantidad, idMueble);
            } else {
                // Manejar el caso en el que no haya suficientes muebles disponibles
                throw new IllegalArgumentException("No hay suficientes muebles disponibles para asignar al aula.");
            }
        }
    }




    //Eliminar Asignacion de Mueble
    public void eliminarAsignacionMuebleAulaById(int id) {
        String sqlEliminarAsignacion = "DELETE FROM aulas_muebles WHERE id = ?";
        jdbcTemplate.update(sqlEliminarAsignacion, id);
    }
    //Eliminar Asignacion de Mueble y regresear Cantidad
    public void eliminarAsignacionMuebleAulaByIdAndReturnQuantity(int id) {
        // Obtener la cantidad y el id del mueble asignado
        String sqlObtenerAsignacion = "SELECT id_mueble, cantidad FROM aulas_muebles WHERE id = ?";
        Map<String, Object> asignacion = jdbcTemplate.queryForMap(sqlObtenerAsignacion, id);

        Integer idMueble = (Integer) asignacion.get("id_mueble");
        Integer cantidad = (Integer) asignacion.get("cantidad");

        // Devolver la cantidad al inventario de muebles
        String sqlActualizarMuebles = "UPDATE muebles SET cantidad = cantidad + ?, asignacion = asignacion - ? WHERE id_mueble = ?";
        jdbcTemplate.update(sqlActualizarMuebles, cantidad, cantidad, idMueble);

        // Eliminar la asignación de la tabla aulas_muebles
        String sqlEliminarAsignacion = "DELETE FROM aulas_muebles WHERE id = ?";
        jdbcTemplate.update(sqlEliminarAsignacion, id);
    }


    //Listar
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


    //Mas Detalles
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

}
