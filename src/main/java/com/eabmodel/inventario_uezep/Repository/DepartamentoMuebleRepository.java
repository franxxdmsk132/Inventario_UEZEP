package com.eabmodel.inventario_uezep.Repository;

import com.eabmodel.inventario_uezep.Entity.DepartamentosMuebles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoMuebleRepository extends JpaRepository<DepartamentosMuebles, Integer> {
}
