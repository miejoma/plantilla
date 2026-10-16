package org.example.pc1.repository.projection;

import java.math.BigDecimal;

/** PROYECCIÓN: interfaz que recibe las columnas de la consulta nativa. Cada getter = un alias del SELECT. */
public interface FacturacionProjection {
    String getNombreAstronomo();
    String getCategoriaTelescopio();
    BigDecimal getMontoTotal();
}
