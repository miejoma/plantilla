package org.example.pc1.service;

import org.example.pc1.dto.*;
import org.example.pc1.entity.*;
import org.example.pc1.exception.RecursoNoEncontradoException;
import org.example.pc1.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Capa de NEGOCIO: aquí va la lógica (validar que existan las FK, armar entidades, convertir a DTO).
 * El controller solo recibe/devuelve; el repository solo accede a datos.
 * @Transactional: cada método corre en una transacción (si hay excepción se revierte todo).
 * Prefijo e2_ en las variables = convención de nombres pedida por el examen (cámbiala por la que pidan).
 */
@Service
@Transactional
public class ObservacionService {
    private final ObservacionRepository e2_observacionRepository;
    private final AstronomoRepository e2_astronomoRepository;
    private final TelescopioRepository e2_telescopioRepository;

    // Inyección de dependencias por constructor (Spring pasa los repositorios solo)
    public ObservacionService(ObservacionRepository e2_observacionRepository,
                              AstronomoRepository e2_astronomoRepository,
                              TelescopioRepository e2_telescopioRepository) {
        this.e2_observacionRepository = e2_observacionRepository;
        this.e2_astronomoRepository = e2_astronomoRepository;
        this.e2_telescopioRepository = e2_telescopioRepository;
    }

    // CREATE (POST): crea una Observacion nueva con los datos del request y la guarda (INSERT)
    public ObservacionResponse registrar(ObservacionRequest e2_request) {
        Observacion e2_observacion = new Observacion();
        copiarDatos(e2_observacion, e2_request);
        return convertir(e2_observacionRepository.save(e2_observacion));
    }

    // READ (GET): devuelve todas. readOnly = true porque solo lee (más eficiente)
    @Transactional(readOnly = true)
    public List<ObservacionResponse> listar() {
        return e2_observacionRepository.findAll().stream().map(this::convertir).toList();
    }

    // UPDATE (PUT): busca por id (si no existe -> 404), pisa los datos y guarda (UPDATE)
    public ObservacionResponse actualizar(Long e2_codigoObservacion, ObservacionRequest e2_request) {
        Observacion e2_observacion = e2_observacionRepository.findById(e2_codigoObservacion)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe la observacion con id " + e2_codigoObservacion));
        copiarDatos(e2_observacion, e2_request);
        return convertir(e2_observacionRepository.save(e2_observacion));
    }

    // DELETE: verifica que exista (si no -> 404) y borra
    public void eliminar(Long e2_codigoObservacion) {
        if (!e2_observacionRepository.existsById(e2_codigoObservacion)) {
            throw new RecursoNoEncontradoException("No existe la observacion con id " + e2_codigoObservacion);
        }
        e2_observacionRepository.deleteById(e2_codigoObservacion);
    }

    // REPORTE: convierte cada fila de la proyección en un FacturacionResponse
    @Transactional(readOnly = true)
    public List<FacturacionResponse> facturacionAstronomoCategoria() {
        return e2_observacionRepository.obtenerFacturacionAstronomoCategoria().stream()
                .map(e2_resultado -> new FacturacionResponse(
                        e2_resultado.getNombreAstronomo(),
                        e2_resultado.getCategoriaTelescopio(),
                        e2_resultado.getMontoTotal()))
                .toList();
    }

    // Método auxiliar compartido por registrar y actualizar: valida las FK y copia los campos del DTO a la entidad.
    // Si el astrónomo o el telescopio no existen -> RecursoNoEncontradoException (404).
    private void copiarDatos(Observacion e2_observacion, ObservacionRequest e2_request) {
        Astronomo e2_astronomo = e2_astronomoRepository.findById(e2_request.astronomoId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el astronomo con id " + e2_request.astronomoId()));
        Telescopio e2_telescopio = e2_telescopioRepository.findById(e2_request.telescopioId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el telescopio con id " + e2_request.telescopioId()));
        e2_observacion.setHoras(e2_request.horas());
        e2_observacion.setFecha(e2_request.fecha());
        e2_observacion.setTarifa(e2_request.tarifa());
        e2_observacion.setEstado(e2_request.estado());
        e2_observacion.setAstronomo(e2_astronomo);
        e2_observacion.setTelescopio(e2_telescopio);
    }

    // Entidad -> DTO de salida (aplana los datos del astrónomo y del telescopio)
    private ObservacionResponse convertir(Observacion e2_observacion) {
        return new ObservacionResponse(
                e2_observacion.getId(), e2_observacion.getHoras(), e2_observacion.getFecha(),
                e2_observacion.getTarifa(), e2_observacion.getEstado(),
                e2_observacion.getAstronomo().getId(), e2_observacion.getAstronomo().getNombre(),
                e2_observacion.getTelescopio().getId(), e2_observacion.getTelescopio().getNombre(),
                e2_observacion.getTelescopio().getCategoria());
    }
}
