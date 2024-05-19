package com.eabmodel.inventario_uezep.Service;

import com.eabmodel.inventario_uezep.Dao.DepartamentosMueblesDao;
import com.eabmodel.inventario_uezep.Dao.MueblesDao;
import com.eabmodel.inventario_uezep.Entity.DepartamentosMuebles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartamentoMuebleService {

    @Autowired
    private  DepartamentosMueblesDao departamentosMueblesDao;
    @Autowired
    private MueblesDao mueblesDao;

    public void AsignarMueblesDepartamentos(int id_Departamentos, List<Integer> idMuebles) {
        departamentosMueblesDao.asignarMuebles(id_Departamentos, idMuebles);
    }
    public void updateDepartamentosMueblesAsignacion(int id_departamentos, int idMuebles){
        departamentosMueblesDao.actualizarAsignacionMueblesDepartamentos(id_departamentos, idMuebles);
    }
    public void eliminarAsigacionMueblesDepartamentos(int id_departamentos, int idMuebles){
        departamentosMueblesDao.eliminarAsignacionMueblesDepartamentos(id_departamentos, idMuebles);
    }
    public void eliminarAsigacionMueblesDepartamentosById(int id){
        departamentosMueblesDao.eliminarAsignacionMueblesDepartamentosById(id);
    }
    public List<DepartamentosMuebles> findAll(){
        return departamentosMueblesDao.findAllDetails3();
    }
    public List<DepartamentosMuebles> findAllGroupBy(){
        return departamentosMueblesDao.findAllDepartamentosMueblesWithDetailsGroupBy();}

    public  List<DepartamentosMuebles> findDetailsByDepartamentosIdWrapper(int id_departamentos){
        return  departamentosMueblesDao.findDetailsByDepartamentosId(id_departamentos);

    }
    public List<DepartamentosMuebles> findMueblesByIdDepartamentos(int id_departamentos){
        return  departamentosMueblesDao.findDetailsByDepartamentosId(id_departamentos);
    }
}
