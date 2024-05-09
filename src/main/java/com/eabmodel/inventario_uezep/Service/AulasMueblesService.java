package com.eabmodel.inventario_uezep.Service;

import com.eabmodel.inventario_uezep.Dao.AulasMueblesDao;
import com.eabmodel.inventario_uezep.Dao.MueblesDao;
import com.eabmodel.inventario_uezep.Entity.Aulas;
import com.eabmodel.inventario_uezep.Entity.AulasMuebles;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AulasMueblesService {

    @Autowired
    private AulasMueblesDao aulasMueblesDao;
    @Autowired
    private MueblesDao mueblesDao;

    public void asignarMueblesAAula(int idAula, List<Integer> idMuebles) {
        aulasMueblesDao.asignarMueblesAAula(idAula, idMuebles);
    }

    public void asignarMueblesAAulaConCantidad(int id_aula, Map<Integer, Integer> mueblesConCantidad) {
        // No es necesario declarar una nueva variable mueblesConCantidad aquí
        // Convertir la lista de IDs de muebles y la cantidad total en un mapa de IDs de muebles con sus cantidades respectivas
        // No necesitas este bloque de código aquí, ya que los datos ya están en el parámetro mueblesConCantidad

        // Pasar el ID del aula y el mapa de IDs de muebles con sus cantidades respectivas al DAO
        aulasMueblesDao.asignarMueblesAAulaConCantidad(id_aula, mueblesConCantidad);
    }

    public void asignarMueblesAAulaConCantidad2(int id_aula, String[] idMuebles, String[] cantidades) {
        // Convertir los arrays de IDs de muebles y cantidades a un mapa de IDs de muebles con sus cantidades respectivas
        Map<Integer, Integer> mueblesConCantidad = new HashMap<>();
        for (int i = 0; i < idMuebles.length; i++) {
            int idMueble = Integer.parseInt(idMuebles[i]);
            int cantidad = Integer.parseInt(cantidades[i]);
            mueblesConCantidad.put(idMueble, cantidad);
        }
        // Pasar el ID del aula y el mapa de IDs de muebles con sus cantidades respectivas al DAO
        aulasMueblesDao.asignarMueblesAAulaConCantidad(id_aula, mueblesConCantidad);
    }

    public void updateAulasMueblesAsignacion(int idAula, int idMueble, int nuevaCantidad) {
        aulasMueblesDao.actualizarAsignacionMuebleAula(idAula, idMueble, nuevaCantidad);
    }

    public void eliminarAsignacionMuebleAula(int idAula, int idMueble) {
        aulasMueblesDao.eliminarAsignacionMuebleAula(idAula, idMueble);
    }
    public void eliminarAsignacionMuebleAulaById(int id) {
        aulasMueblesDao.eliminarAsignacionMuebleAulaById(id);
    }


    public List<AulasMuebles> findAll() {
        return aulasMueblesDao.findAllWithDetails3();
    }

    public List<AulasMuebles> findAllGroupBy() {
        return aulasMueblesDao.findAllAulasMueblesWithDetailsGroupBy();
    }

    //mas detalles
    public List<AulasMuebles> findDetailsByAulaIdWrapper(int id_aula) {
        return aulasMueblesDao.findDetailsByAulaId(id_aula);
    }

    //para actualizar
    public List<AulasMuebles> findMueblesByIdAula(int id_aula) {
        return aulasMueblesDao.findDetailsByAulaId(id_aula);
    }


}
