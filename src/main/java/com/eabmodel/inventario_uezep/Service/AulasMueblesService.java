package com.eabmodel.inventario_uezep.Service;

import com.eabmodel.inventario_uezep.Dao.AulasMueblesDao;
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

    // Otros métodos del servicio, si es necesario
}
