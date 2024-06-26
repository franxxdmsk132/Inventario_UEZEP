package com.eabmodel.inventario_uezep.Service;

import com.eabmodel.inventario_uezep.Dao.MueblesDao;
import com.eabmodel.inventario_uezep.Entity.Muebles;
import com.eabmodel.inventario_uezep.Repository.MueblesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MuebleService {

    @Autowired
    private MueblesDao mueblesDao;

    public List<Muebles> findAll() {
        return mueblesDao.findAll();
    }

    public Muebles findById(int id_mueble) {
        Muebles muebles = mueblesDao.findByIdMueble(id_mueble);
        return muebles;
    }

    public void save(Muebles muebles) {
        mueblesDao.save(muebles);
    }

    public void update(Muebles muebles) {
        mueblesDao.update3(muebles);
    }

    public void deleteByIdMueble(int id_mueble) {
        mueblesDao.delete2(id_mueble);
    }

    public void cambiarEstadoByIdMueble(int id_mueble) {
        mueblesDao.cambiarEstado(id_mueble);
    }


}
