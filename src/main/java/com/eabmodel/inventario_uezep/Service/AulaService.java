package com.eabmodel.inventario_uezep.Service;

import com.eabmodel.inventario_uezep.Dao.AulasDao;
import com.eabmodel.inventario_uezep.Entity.Aulas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AulaService {
    @Autowired
    private AulasDao aulasDao;

    public List<Aulas> findAll(){
        return aulasDao.findAll();
    }
    public Aulas findById(int id_aula){
        Aulas aulas = aulasDao.findByIdAula(id_aula);
        return aulas;
    }
    public void save(Aulas aulas){
        aulasDao.save(aulas);
    }
    public void update(Aulas aulas){
        aulasDao.update(aulas);
    }
    public void deleteByIdAula(int id_aula) {
        aulasDao.delete(id_aula);
    }

}
