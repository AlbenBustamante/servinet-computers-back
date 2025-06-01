package com.servinetcomputers.api.core.exception;

/**
 * Respuesta para el manejo de errores.
 *
 * @param message    Mensaje a mostrar al cliente.
 * @param path       Ruta donde ocurre el error.
 * @param error      Mensaje de Error HTTP.
 * @param statusCode Código de Error HTTP.
 * @param timestamp  Fecha y hora exacta formateada del error.
 */
public record ErrorResponse(
        String message,
        String path,
        String error,
        int statusCode,
        String timestamp
) {
}
