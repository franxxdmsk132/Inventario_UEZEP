package com.eabmodel.inventario_uezep.Dao;

import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.RowMapper.MueblesRowMapper;
import com.eabmodel.inventario_uezep.Service.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class MueblesDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;


    public void save(Muebles muebles) {
        String sql = "INSERT INTO `muebles`(codigo_mueble, nombre_mueble, descripcion_mueble, fecha_ingreso, estado, cantidad, asignacion) VALUES ( ?, ?, ?, ?, ?, ?,0)";

        jdbcTemplate.update(
                sql,
                muebles.getCodigo_mueble(),
                muebles.getNombre_mueble(),
                muebles.getDescripcion_mueble(),
                muebles.getFecha_ingreso(),
                muebles.getEstado(),
                muebles.getCantidad()
        );
    }
    public void save2(Muebles muebles) {
        // Consulta para verificar si el código ya existe
        String sqlCheck = "SELECT COUNT(*) FROM muebles WHERE codigo_mueble = ?";
        int count = jdbcTemplate.queryForObject(sqlCheck, Integer.class, muebles.getCodigo_mueble());

        // Si count es mayor que 0, significa que el código ya existe
        if (count > 0) {
            // Manejar la situación cuando el código ya está en uso
            // Por ejemplo, puedes lanzar una excepción o mostrar un mensaje de error
            throw new IllegalStateException("El código de mueble ya está en uso.");
        } else {
            // El código es único, proceder con la inserción
            String sqlInsert = "INSERT INTO `muebles`(codigo_mueble, nombre_mueble, descripcion_mueble, fecha_ingreso, estado, cantidad, asignacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(
                    sqlInsert,
                    muebles.getCodigo_mueble(),
                    muebles.getNombre_mueble(),
                    muebles.getDescripcion_mueble(),
                    muebles.getFecha_ingreso(),
                    muebles.getEstado(),
                    muebles.getCantidad(),
                    muebles.getAsignacion()
            );
        }
    }


    public List<Muebles> findAll() {
        String sql = "SELECT * FROM `muebles` ";
        return jdbcTemplate.query(sql, new MueblesRowMapper());
    }

    public Muebles findByIdMueble(int id_mueble) {
        String sql = "SELECT * FROM muebles WHERE id_mueble = ? ";
        return jdbcTemplate.queryForObject(sql, new MueblesRowMapper(), id_mueble);
    }

        public void update3(Muebles muebles) {
        String sql = "UPDATE muebles SET asignacion = ?, cantidad = ?, codigo_mueble = ?, estado = ?, fecha_ingreso = ?, nombre_mueble = ?, descripcion_mueble = ? WHERE id_mueble = ?";
        jdbcTemplate.update(sql, muebles.getAsignacion(), muebles.getCantidad(), muebles.getCodigo_mueble(), muebles.getEstado(), muebles.getFecha_ingreso(), muebles.getNombre_mueble(), muebles.getDescripcion_mueble(), muebles.getId_mueble());
    }

    public void update(Muebles muebles) {
        // Obtener los muebles existentes antes de la actualización
        Muebles mueblesAntiguos = findByIdMueble(muebles.getId_mueble());

        // Calcular la diferencia entre los valores antiguos y nuevos
        int diferenciaCantidad = muebles.getCantidad() - mueblesAntiguos.getCantidad();
        int diferenciaAsignacion = muebles.getAsignacion() - mueblesAntiguos.getAsignacion();

        // Actualizar la cantidad de acuerdo a la diferencia entre los valores antiguos y nuevos de asignación
        int nuevaCantidad = mueblesAntiguos.getCantidad() + diferenciaAsignacion;
        if (nuevaCantidad < 0) {
            // Si la nueva cantidad es negativa, no se puede realizar la actualización
            throw new RuntimeException("La cantidad resultante es negativa");
        }

        // Actualizar la cantidad en la base de datos
        String sqlActualizarCantidad = "UPDATE muebles SET cantidad = ? WHERE id_mueble = ?";
        jdbcTemplate.update(sqlActualizarCantidad, nuevaCantidad, muebles.getId_mueble());

        // Actualizar los muebles en la base de datos, incluida la asignación
        String sql = "UPDATE muebles SET asignacion = ?, codigo_mueble = ?, estado = ?, fecha_ingreso = ?, nombre_mueble = ?, descripcion_mueble = ? WHERE id_mueble = ?";
        jdbcTemplate.update(sql, muebles.getAsignacion(), muebles.getCodigo_mueble(), muebles.getEstado(), muebles.getFecha_ingreso(), muebles.getNombre_mueble(), muebles.getDescripcion_mueble(), muebles.getId_mueble());
    }



    public void update2(Muebles muebles) {
        // Obtener los muebles existentes antes de la actualización
        Muebles mueblesAntiguos = findByIdMueble(muebles.getId_mueble());

        // Calcular la diferencia entre los valores antiguos y nuevos de asignación y cantidad
        int diferenciaAsignacion = muebles.getAsignacion() - mueblesAntiguos.getAsignacion();
        int diferenciaCantidad = muebles.getCantidad() - mueblesAntiguos.getCantidad();

        // Actualizar la cantidad de acuerdo a la diferencia entre los valores antiguos y nuevos de asignación
        if (diferenciaAsignacion != 0) {
            // Restar la diferencia de asignación a la cantidad
            int nuevaCantidad = mueblesAntiguos.getCantidad() - diferenciaAsignacion;

            // Verificar que la cantidad resultante no sea negativa
            if (nuevaCantidad >= 0) {
                // Actualizar la cantidad en la base de datos
                String sqlActualizarCantidad = "UPDATE muebles SET cantidad = ? WHERE id_mueble = ?";
                jdbcTemplate.update(sqlActualizarCantidad, nuevaCantidad, muebles.getId_mueble());
            } else {
                // Manejar el caso de cantidad negativa de acuerdo a tus necesidades
                throw new RuntimeException("La cantidad resultante es negativa");
            }
        }

        // Actualizar los muebles en la base de datos, incluida la asignación y la cantidad
        String sql = "UPDATE muebles SET asignacion = ?, cantidad = ?, codigo_mueble = ?, estado = ?, fecha_ingreso = ?, nombre_mueble = ?, descripcion_mueble = ? WHERE id_mueble = ?";
        jdbcTemplate.update(sql, muebles.getAsignacion(), muebles.getCantidad(), muebles.getCodigo_mueble(), muebles.getEstado(), muebles.getFecha_ingreso(), muebles.getNombre_mueble(), muebles.getDescripcion_mueble(), muebles.getId_mueble());
    }



    //    public void updateEstado(Muebles muebles) {
//        String sql = "UPDATE `muebles` SET `estado` = ? WHERE `muebles`.`id_mueble` = ?";
//        jdbcTemplate.update(sql, muebles.getEstado(), muebles.getId_mueble());
//    }
    public void delete(int id_mueble) {
        String sql = "DELETE FROM muebles WHERE id_mueble = ?";
        jdbcTemplate.update(sql, id_mueble);
    }
    public void delete2(int id_mueble) {
        // Obtener todas las asignaciones del mueble
        String sqlObtenerAsignaciones = "SELECT id, cantidad FROM aulas_muebles WHERE id_mueble = ?";
        List<Map<String, Object>> asignaciones = jdbcTemplate.queryForList(sqlObtenerAsignaciones, id_mueble);

        // Devolver la cantidad al inventario de muebles y eliminar cada asignación
        for (Map<String, Object> asignacion : asignaciones) {
            Integer idAsignacion = (Integer) asignacion.get("id");
            Integer cantidad = (Integer) asignacion.get("cantidad");

            // Actualizar la cantidad en la tabla 'muebles'
            String sqlActualizarMuebles = "UPDATE muebles SET cantidad = cantidad + ?, asignacion = asignacion - ? WHERE id_mueble = ?";
            jdbcTemplate.update(sqlActualizarMuebles, cantidad, cantidad, id_mueble);

            // Eliminar la asignación de la tabla 'aulas_muebles'
            String sqlEliminarAsignacion = "DELETE FROM aulas_muebles WHERE id = ?";
            jdbcTemplate.update(sqlEliminarAsignacion, idAsignacion);
        }

        // Finalmente, eliminar el mueble de la tabla 'muebles'
        String sqlEliminarMueble = "DELETE FROM muebles WHERE id_mueble = ?";
        jdbcTemplate.update(sqlEliminarMueble, id_mueble);
    }

    public void cambiarEstado(int id_mueble) {
        // Primero, obtén el estado actual del mueble
        String sqlEstado = "SELECT estado FROM muebles WHERE id_mueble = ?";
        boolean estado = jdbcTemplate.queryForObject(sqlEstado, Boolean.class, id_mueble);

        // Luego, cambia el estado al opuesto
        boolean nuevoEstado = !estado;

        // Finalmente, actualiza el estado en la base de datos
        String sqlUpdate = "UPDATE muebles SET estado = ? WHERE id_mueble = ?";
        jdbcTemplate.update(sqlUpdate, nuevoEstado, id_mueble);
    }



}
