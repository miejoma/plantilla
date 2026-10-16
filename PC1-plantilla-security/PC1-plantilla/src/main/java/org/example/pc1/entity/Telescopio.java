package org.example.pc1.entity;

import jakarta.persistence.*;
import lombok.*;

/** Tabla 'telescopio'. Lado "uno" de la relación Telescopio 1 - N Observacion. */
@Entity
@Table(name = "telescopio")
// Lombok: getters, setters y constructores
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Telescopio {
    // PK autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Los tres campos son obligatorios (NOT NULL)
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String ubicacion;
}
