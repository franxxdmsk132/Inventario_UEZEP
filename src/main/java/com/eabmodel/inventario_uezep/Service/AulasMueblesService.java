package com.eabmodel.inventario_uezep.Service;

import com.eabmodel.inventario_uezep.Dao.AulasMueblesDao;
import com.eabmodel.inventario_uezep.Entity.Aulas;
import com.eabmodel.inventario_uezep.Entity.AulasMuebles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AulasMueblesService {

    @Autowired
    private AulasMueblesDao aulasMueblesDao;

    public void asignarMueblesAAula(int idAula, List<Integer> idMuebles) {
        aulasMueblesDao.asignarMueblesAAula(idAula, idMuebles);
    }

    public List<AulasMuebles> findAll() {
        return aulasMueblesDao.findAllWithDetails3();
    }

    public List<AulasMuebles> findAllGroupBy() {
        return aulasMueblesDao.findAllAulasMueblesWithDetailsGroupBy();
    }

    public List<AulasMuebles> findDetailsByAulaIdWrapper(int id_aula) {
        return aulasMueblesDao.findDetailsByAulaId(id_aula);
    }
}
