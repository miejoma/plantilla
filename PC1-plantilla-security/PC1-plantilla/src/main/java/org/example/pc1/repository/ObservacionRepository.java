package org.example.pc1.repository;

import org.example.pc1.entity.Observacion;
import org.example.pc1.repository.projection.FacturacionProjection;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio = acceso a datos. Al extender JpaRepository ya se tienen sin escribir código:
 * save, findAll, findById, existsById, deleteById, count...
 */
public interface ObservacionRepository extends JpaRepository<Observacion, Long> {
    // REPORTE (consulta nativa SQL): agrupa por astrónomo y categoría de telescopio y suma horas*tarifa.
    // OJO: en nativeQuery se usan nombres de TABLAS/COLUMNAS (astronomo_id), no de clases/atributos.
    // Los alias (AS nombreAstronomo...) deben coincidir con los getters de FacturacionProjection (getNombreAstronomo...).
    // Para otras variantes de reporte ver MODIFICACIONES.md (sección 3).
    @Query(value = """
            SELECT a.nombre AS nombreAstronomo, t.categoria AS categoriaTelescopio,
                   SUM(o.horas * o.tarifa) AS montoTotal
            FROM observacion o
            INNER JOIN astronomo a ON a.id = o.astronomo_id
            INNER JOIN telescopio t ON t.id = o.telescopio_id
            GROUP BY a.nombre, t.categoria
            ORDER BY a.nombre, t.categoria
            """, nativeQuery = true)
    List<FacturacionProjection> obtenerFacturacionAstronomoCategoria();
}
