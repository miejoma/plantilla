package org.example.pc1.exception;

/** Excepción propia: se lanza cuando un id no existe (observación, astrónomo o telescopio). Se traduce a 404. */
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
