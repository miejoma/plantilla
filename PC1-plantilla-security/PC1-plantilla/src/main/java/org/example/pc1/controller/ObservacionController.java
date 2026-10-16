package org.example.pc1.controller;

import jakarta.validation.Valid;
import org.example.pc1.dto.*;
import org.example.pc1.service.ObservacionService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Capa WEB: recibe las peticiones HTTP, valida el JSON y delega al service.
 *
 * SEGURIDAD (lo único nuevo respecto al ejercicio base): cada endpoint se protege con @PreAuthorize.
 *   - Sin token o token inválido ........ 401 Unauthorized (lo responde JwtAuthenticationEntryPoint)
 *   - Token válido pero rol incorrecto .. 403 Forbidden
 *   - hasRole('X') busca la autoridad ROLE_X. JwtUserDetailsService ya antepone "ROLE_", así que
 *     aquí se escribe el nombre SIN prefijo y EXACTAMENTE igual al de la tabla roles (ADMINISTRADOR, OPERADOR).
 */
@RestController
@RequestMapping("/api/observacion")
public class ObservacionController {
    private final ObservacionService e1_observacionService;

    // Spring inyecta el service por constructor
    public ObservacionController(ObservacionService e1_observacionService) {
        this.e1_observacionService = e1_observacionService;
    }

    // CREATE -> POST /api/observacion/registrar  | responde 201 Created | roles: ADMINISTRADOR u OPERADOR
    // @Valid activa las validaciones del DTO; @RequestBody convierte el JSON a objeto Java
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR')")
    @PostMapping("/registrar")
    public ResponseEntity<ObservacionResponse> registrar(@Valid @RequestBody ObservacionRequest e1_request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(e1_observacionService.registrar(e1_request));
    }

    // READ -> GET /api/observacion/listar | roles: ADMINISTRADOR u OPERADOR
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','OPERADOR')")
    @GetMapping("/listar")
    public List<ObservacionResponse> listar() {
        return e1_observacionService.listar();
    }

    // UPDATE -> PUT /api/observacion/actualizar/{id} | solo ADMINISTRADOR
    // @PathVariable toma el id de la URL; el body trae los nuevos datos
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("/actualizar/{id}")
    public ObservacionResponse actualizar(@PathVariable("id") Long e1_codigoObservacion,
                                          @Valid @RequestBody ObservacionRequest e1_request) {
        return e1_observacionService.actualizar(e1_codigoObservacion, e1_request);
    }

    // DELETE -> DELETE /api/observacion/eliminar/{id} | responde 204 No Content (sin body) | solo ADMINISTRADOR
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable("id") Long e1_codigoObservacion) {
        e1_observacionService.eliminar(e1_codigoObservacion);
    }

    // REPORTE -> GET /api/observacion/facturacionAstronomoCategoria | solo ADMINISTRADOR
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/facturacionAstronomoCategoria")
    public List<FacturacionResponse> facturacionAstronomoCategoria() {
        return e1_observacionService.facturacionAstronomoCategoria();
    }
}
