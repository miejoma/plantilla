package org.example.pc1.repository;

import org.example.pc1.entity.Astronomo;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD básico de Astronomo (sin métodos propios)
public interface AstronomoRepository extends JpaRepository<Astronomo, Long> {
}
