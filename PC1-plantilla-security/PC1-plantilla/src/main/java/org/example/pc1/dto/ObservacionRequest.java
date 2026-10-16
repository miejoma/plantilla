package org.example.pc1.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de ENTRADA (lo que el cliente envía en el JSON del POST y del PUT).
 * 'record' = clase inmutable compacta (Java 16+). Las anotaciones validan el JSON; si fallan -> 400 Bad Request
 * (lo traduce ApiExceptionHandler).
 *   @NotNull  -> el campo no puede faltar        @Positive -> debe ser mayor a 0
 * Las FK llegan como ids (astronomoId, telescopioId), NO como objetos completos.
 */
public record ObservacionRequest(
        @NotNull @Positive Integer horas,
        @NotNull LocalDate fecha,
        @NotNull @Positive BigDecimal tarifa,
        @NotNull Boolean estado,
        @NotNull @Positive Long astronomoId,
        @NotNull @Positive Long telescopioId
) {
}
