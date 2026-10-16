package org.example.pc1.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de SALIDA (lo que se devuelve al cliente). Se aplana la información de las relaciones
 * (nombre del astrónomo, nombre y categoría del telescopio) para no exponer las entidades JPA directamente.
 */
public record ObservacionResponse(
        Long id,
        Integer horas,
        LocalDate fecha,
        BigDecimal tarifa,
        Boolean estado,
        Long astronomoId,
        String astronomoNombre,
        Long telescopioId,
        String telescopioNombre,
        String categoriaTelescopio
) {
}
