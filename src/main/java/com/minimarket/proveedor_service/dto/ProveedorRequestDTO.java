package com.minimarket.proveedor_service.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos necesarios para registrar un proveedor")
public class ProveedorRequestDTO {

    @Schema(
            description = "RUT del proveedor",
            example = "12.345.678-9"
    )
    @NotBlank(message= "EL RUT ES OBLIGATORIO")
    private String rut;

    @Schema(
            description = "Nombre del proveedor",
            example = "Distribuidora Central"
    )
    @NotBlank(message="EL NOMBRE ES OBLIGATORIO")
    private String nombre;

    @Schema(
            description = "Correo electrónico",
            example = "contacto@empresa.cl"
    )
    @NotBlank(message="EL EMAIL ES OBLIGATORIO")
    @Email(message="EL EMAIL DEBE SER VÁLIDO.")
    private String email;

    @Schema(
            description = "Teléfono de contacto",
            example = "+56912345678"
    )
    @NotBlank(message="EL TELEFÓNO ES OBLIGATORIO")
    private String telefono;

    @Schema(
            description = "Dirección del proveedor",
            example = "Av. Libertad 123, Viña del Mar"
    )
    @NotBlank(message="EL DIRECCIÓN ES OBLIGATORIA")
    private String direccion;
}
