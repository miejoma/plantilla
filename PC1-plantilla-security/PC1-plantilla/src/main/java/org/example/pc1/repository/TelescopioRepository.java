package org.example.pc1.repository;

import org.example.pc1.entity.Telescopio;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD básico de Telescopio (sin métodos propios)
public interface TelescopioRepository extends JpaRepository<Telescopio, Long> {
}
