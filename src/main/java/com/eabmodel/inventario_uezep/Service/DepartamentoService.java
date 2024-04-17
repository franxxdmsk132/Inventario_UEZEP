package com.eabmodel.inventario_uezep.Service;


import com.eabmodel.inventario_uezep.Dao.DepartamentosDao;
import com.eabmodel.inventario_uezep.Entity.Departamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartamentoService {
    @Autowired
    private DepartamentosDao departamentosDao;

    public List<Departamentos> findAll(){
        return departamentosDao.findAll();
    }

    public Departamentos findById(int id_departamentos){
        Departamentos departamentos = departamentosDao.findByIdDepartamentos(id_departamentos);
        return departamentos;
    }
    public void save(Departamentos departamentos){
        departamentosDao.save(departamentos);
    }
    public  void update (Departamentos departamentos){ departamentosDao.update(departamentos);}
    public void deleteByIdDepartamentos(int id_departamentos){ departamentosDao.delete(id_departamentos);}
}
