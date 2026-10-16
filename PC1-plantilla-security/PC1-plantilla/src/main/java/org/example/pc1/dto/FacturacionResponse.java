package org.example.pc1.dto;

import java.math.BigDecimal;

/** DTO de SALIDA del reporte agregado: una fila por (astrónomo, categoría) con la suma horas*tarifa. */
public record FacturacionResponse(
        String nombreAstronomo,
        String categoriaTelescopio,
        BigDecimal montoTotal
) {
}
