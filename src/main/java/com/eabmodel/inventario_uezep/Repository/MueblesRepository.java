package com.eabmodel.inventario_uezep.Repository;

import com.eabmodel.inventario_uezep.Entity.Muebles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MueblesRepository extends JpaRepository <Muebles, Integer> {
}
