package com.eabmodel.inventario_uezep.Repository;

import com.eabmodel.inventario_uezep.Entity.AulasMuebles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulasMueblesRepository extends JpaRepository<AulasMuebles, Integer> {
}
