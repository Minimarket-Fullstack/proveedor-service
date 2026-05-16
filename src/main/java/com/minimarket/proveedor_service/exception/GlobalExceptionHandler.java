package com.minimarket.proveedor_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProveedorNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProveedorNotFound(ProveedorNotFoundException pvex){
        log.warn("PROVEEDOR NO ENCONTRADO: {}", pvex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("ERROR", "PROVEEDOR NO ENCONTRADO", "DETALLE", pvex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> errores = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errores.put(error.getField(),error.getDefaultMessage()));
        log.warn("[VALIDACIÓN] Petición rechazada. Campos con error: {}", ex.toString());
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,String>> handleDuplicate(DataIntegrityViolationException dx){
        log.error("RESTRICCIÓN DE CONSTRAINT: {}", dx);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("ERROR", "EL RUT O EMAIL YA ESTAN REGISTRADOS"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex){
        log.warn("OCURRIÓ UN ERROR INESPERADO: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error interno en el servidor"));

    }



}
