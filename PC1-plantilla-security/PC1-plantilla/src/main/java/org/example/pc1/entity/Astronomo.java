package org.example.pc1.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * ENTIDAD = una tabla de la BD. Esta clase crea la tabla 'astronomo' (Hibernate la genera sola
 * por spring.jpa.hibernate.ddl-auto=update). Es el lado "uno" de la relación Astronomo 1 - N Observacion.
 */
@Entity
@Table(name = "astronomo")
// Lombok: genera getters, setters, constructor vacío y constructor con todos los campos
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Astronomo {
    // Llave primaria autoincremental (serial/identity en PostgreSQL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable = false  ->  columna NOT NULL (obligatoria)
    @Column(nullable = false)
    private String nombre;

    // NOT NULL + UNIQUE: obligatorio y no se puede repetir (si se repite: error de clave duplicada)
    @Column(nullable = false, unique = true)
    private String email;

    // Sin @Column: opcional (acepta null)
    private String telefono;
}
