package com.minimarket.proveedor_service.exception;

public class ProveedorNotFoundException extends RuntimeException {
    public ProveedorNotFoundException(Long id) {
        super("PROVEEDOR CON EL ID " + id + " NO ENCONTRADO");
    }
}
