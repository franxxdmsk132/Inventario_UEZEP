package com.eabmodel.inventario_uezep.Repository;

import com.eabmodel.inventario_uezep.Entity.Departamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamentos, Integer> {
}
