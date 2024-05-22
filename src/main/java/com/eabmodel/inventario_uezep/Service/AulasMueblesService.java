package com.eabmodel.inventario_uezep.Service;

import com.eabmodel.inventario_uezep.Dao.AulasMueblesDao;
import com.eabmodel.inventario_uezep.Dao.MueblesDao;
import com.eabmodel.inventario_uezep.Entity.AulasMuebles;
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

    //Guardar Muebles Con Cantidad
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

    //Eliminar Muebles Asignados y regresar Cantidad
    public void eliminarAsignacionMuebleAulaByIdYRegresarCantidad(int id) {
        aulasMueblesDao.eliminarAsignacionMuebleAulaByIdAndReturnQuantity(id);
    }

    //Listar
    public List<AulasMuebles> findAll() {
        return aulasMueblesDao.findAllWithDetails3();
    }

    //Mas detalles
    public List<AulasMuebles> findDetailsByAulaIdWrapper(int id_aula) {
        return aulasMueblesDao.findDetailsByAulaId(id_aula);
    }


}
