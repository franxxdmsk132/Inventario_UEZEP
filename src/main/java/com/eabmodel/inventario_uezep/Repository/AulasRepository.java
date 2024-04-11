package com.eabmodel.inventario_uezep.Repository;

import com.eabmodel.inventario_uezep.Entity.Aulas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulasRepository extends JpaRepository<Aulas, Integer> {
}
