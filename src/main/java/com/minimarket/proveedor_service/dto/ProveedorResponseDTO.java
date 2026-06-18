package com.minimarket.proveedor_service.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de proveedor")
public class ProveedorResponseDTO {

    @Schema(
            description = "ID del proveedor",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "RUT del proveedor",
            example = "12.345.678-9"
    )
    private String rut;

    @Schema(
            description = "Nombre del proveedor",
            example = "Distribuidora Central"
    )
    private String nombre;

    @Schema(
            description = "Correo electrónico",
            example = "contacto@empresa.cl"
    )
    private String email;

    @Schema(
            description = "Teléfono de contacto",
            example = "+56912345678"
    )
    private String telefono;

    @Schema(
            description = "Dirección del proveedor",
            example = "Av. Libertad 123, Viña del Mar"
    )
    private String direccion;
}
