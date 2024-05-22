package com.eabmodel.inventario_uezep.Dao;

import com.eabmodel.inventario_uezep.Entity.AulasMuebles;
import com.eabmodel.inventario_uezep.Entity.DepartamentosMuebles;
import com.eabmodel.inventario_uezep.RowMapper.AulasMueblesRowMapper;
import com.eabmodel.inventario_uezep.RowMapper.DepartamentoMuebleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class DepartamentosMueblesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

   public void asignarMueblesADepartamentosConCantidad(int idDepartamento, Map<Integer, Integer>mueblesconCantidad){
   for (Map.Entry<Integer, Integer>entry : mueblesconCantidad.entrySet()){
   Integer idMueble = entry.getKey();
   Integer cantidad = entry.getValue();

   if (cantidad < 0) {
       cantidad = 0;
   }
   String sqlMueblesDisponibles = "SELECT cantidad FROM muebles WHERE id_mueble =?";
   Integer cantidadDisponible = jdbcTemplate.queryForObject(sqlMueblesDisponibles, Integer.class, idMueble);

   if (cantidadDisponible >= cantidad){
       String sqlExisteAsignacion = "SELECT COUNT (*) FROM departamentos_muebles (id_departamento, id_mueble, cantidad) VALUES (?,?,?)";
       int count = jdbcTemplate.queryForObject(sqlExisteAsignacion, Integer.class, idDepartamento, idMueble);

       if (count == 0) {
           String sqlAsignacionMueble = "INSERT INTO departamentos_muebles (id_departamento, id_mueble, cantidad) VALUES (?,?,?)";
           jdbcTemplate.update(sqlAsignacionMueble, idDepartamento, idMueble, cantidad);

       } else {
           String sqlActualizarAsignacion = "UPDATE muebles SET cantidad = cantidad - ?, asignacion = asignacion + ? WHERE id_mueble = ?";
       jdbcTemplate.update(sqlActualizarAsignacion, cantidad,idDepartamento, idMueble);
       }
       String sqlActualizarMuebles = "UPDATE muebles SET cantidad = cantidad - ?, asignacion = asignacion + ? WHERE id_mueble = ?";
       jdbcTemplate.update(sqlActualizarMuebles, cantidad, cantidad, idMueble);
   } else {
       // Manejar el caso en el que no haya suficientes muebles disponibles
       throw new IllegalArgumentException("No hay suficientes muebles disponibles para asignar al aula.");
   }
   }
   }
    public void eliminarAsignacionMuebleDepartamentoById(int id) {
        String sqlEliminarAsignacion = "DELETE FROM departamentos_muebles WHERE id = ?";
        jdbcTemplate.update(sqlEliminarAsignacion, id);
    }
    public void  eliminarAsignacionMueblesDepartamentosByIdAndReturnQuantity(int id){
       String sqlobtenerAsignacion = "SELECT id_mueble, cantidad FROM departamentos_muebles WHERE id = ?";
       Map<String, Object> asignacion = jdbcTemplate.queryForMap(sqlobtenerAsignacion, id);

       Integer idMueble = (Integer) asignacion.get("id_mueble");
       Integer cantidad = ( Integer) asignacion.get("cantidad");

       String sqlActualizarMuebles= "UPDATE muebles SET cantidad = cantidad + ?, asignacion = asignacion - ? WHERE id_mueble = ?";
        jdbcTemplate.update(sqlActualizarMuebles, cantidad, cantidad, idMueble);

        String sqlEliminarAsignacion = "DELETE FROM departamentos_muebles WHERE id = ?";
   }
    public List<DepartamentosMuebles> findAllWithDetails3() {
        String sql = "SELECT \n" +
                "\t*,\n" +
                "    d.*,\n" +
                "    m.*\n" +
                "FROM\n" +
                "    departamentos_muebles dm\n" +
                "JOIN departamentos d ON\n" +
                "    dm.id_departamento = d.id_departamento\n" +
                "JOIN muebles m ON\n" +
                "    dm.id_mueble = m.id_mueble\n" +
                "GROUP BY\n" +
                "    d.id_departamento;\n";
        return jdbcTemplate.query(sql, new DepartamentoMuebleRowMapper());
    }
    public List<DepartamentosMuebles> findDetailsByDepartamentoId(int id_departamento) {
        String sql = "SELECT\n" +
                "    dm.*,\n" +
                "    d.*,\n" +
                "    m.*\n" +
                "FROM\n" +
                "    departamentos_muebles dm\n" +
                "INNER JOIN departamentos d ON\n" +
                "    dm.id_departamento = a.id_departamento\n" +
                "INNER JOIN muebles m ON\n" +
                "    dm.id_mueble = m.id_mueble\n" +
                "WHERE\n" +
                "    d.id_departamento = ?";
        return jdbcTemplate.query(sql, new DepartamentoMuebleRowMapper(), id_departamento);
   }
}