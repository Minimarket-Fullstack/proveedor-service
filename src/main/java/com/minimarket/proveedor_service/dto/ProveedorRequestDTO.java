package com.minimarket.proveedor_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorRequestDTO {

    @NotBlank(message= "EL RUT ES OBLIGATORIO")
    private String rut;

    @NotBlank(message="EL NOMBRE ES OBLIGATORIO")
    private String nombre;

    @NotBlank(message="EL EMAIL ES OBLIGATORIO")
    @Email(message="EL EMAIL DEBE SER VÁLIDO.")
    private String email;

    @NotBlank(message="EL TELEFÓNO ES OBLIGATORIO")
    private String telefono;

    @NotBlank(message="EL DIRECCIÓN ES OBLIGATORIA")
    private String direccion;




}
