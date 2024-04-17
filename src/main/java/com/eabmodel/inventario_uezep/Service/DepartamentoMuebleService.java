package com.eabmodel.inventario_uezep.Service;

import com.eabmodel.inventario_uezep.Dao.DepartamentosMueblesDao;
import com.eabmodel.inventario_uezep.Entity.DepartamentosMuebles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartamentoMuebleService {

    @Autowired
    private DepartamentosMueblesDao departamentosMueblesDao;

    public  void  asiganarMuebleDepartamento(int idDepartamento, List<Integer>idMuebles) {
        departamentosMueblesDao.asignarMuebles(idDepartamento, idMuebles);
    }
    public  List<DepartamentosMuebles> findAll(){
        return departamentosMueblesDao.findAllWithDetails();

    }
}
