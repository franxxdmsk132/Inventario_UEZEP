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

    public List<Muebles> findAll(){
        return mueblesDao.findAll();
    }
    public void save (Muebles mueblesxd){
        mueblesDao.save(mueblesxd);
    }

}
