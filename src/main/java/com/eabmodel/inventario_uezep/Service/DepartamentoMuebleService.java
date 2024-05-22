package com.eabmodel.inventario_uezep.Service;

import com.eabmodel.inventario_uezep.Dao.DepartamentosMueblesDao;
import com.eabmodel.inventario_uezep.Entity.DepartamentosMuebles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DepartamentoMuebleService {

    private final DepartamentosMueblesDao departamentosMueblesDao;

    @Autowired
    public DepartamentoMuebleService(DepartamentosMueblesDao departamentosMueblesDao) {
        this.departamentosMueblesDao = departamentosMueblesDao;
    }

    public void asignarMueblesADepartamentoConCantidad(int idDepartamento, String[] idMuebles, String[] cantidades) {
        Map<Integer, Integer> mueblesConCantidad = new HashMap<>();
        for (int i = 0; i < idMuebles.length; i++) {
            int idMueble = Integer.parseInt(idMuebles[i]);
            int cantidad = Integer.parseInt(cantidades[i]);
            mueblesConCantidad.put(idMueble, cantidad);
        }
        departamentosMueblesDao.asignarMueblesADepartamentosConCantidad(idDepartamento, mueblesConCantidad);
    }

    public void eliminarAsignacionMuebleDepartamentoById(int id) {
        departamentosMueblesDao.eliminarAsignacionMuebleDepartamentoById(id);
    }

    public void eliminarAsignacionMuebleDepartamentoByIdRegresarCantidad(int id) {
        departamentosMueblesDao.eliminarAsignacionMueblesDepartamentosByIdAndReturnQuantity(id);
    }

    public List<DepartamentosMuebles> findAll() {
        return departamentosMueblesDao.findAllWithDetails3();
    }

    public List<DepartamentosMuebles> findDetailsByDepartamentoIdWrapper(int idDepartamento) {
        return departamentosMueblesDao.findDetailsByDepartamentoId(idDepartamento);
    }
}
