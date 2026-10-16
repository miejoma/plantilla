package org.example.pc1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Tabla 'observacion'. Es la tabla "del medio": cada observación pertenece a UN astrónomo y a UN telescopio
 * (dos llaves foráneas). Es la entidad principal sobre la que se hace el CRUD y el reporte.
 */
@Entity
@Table(name = "observacion")
// Lombok: getters, setters y constructores
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Observacion {
    // PK autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cantidad de horas observadas (obligatorio)
    @Column(nullable = false)
    private Integer horas;

    @Column(nullable = false)
    private LocalDate fecha;

    // BigDecimal para dinero (no usar Double). precision 10 = dígitos totales, scale 2 = decimales
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifa;

    @Column(nullable = false)
    private Boolean estado;

    // FK hacia Astronomo: muchas observaciones -> un astrónomo.
    // LAZY = el astrónomo se carga solo cuando se usa; optional=false/nullable=false = la FK es obligatoria.
    // Crea la columna 'astronomo_id' en la tabla observacion.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "astronomo_id", nullable = false)
    private Astronomo astronomo;

    // FK hacia Telescopio (misma idea). Crea la columna 'telescopio_id'.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "telescopio_id", nullable = false)
    private Telescopio telescopio;
}
