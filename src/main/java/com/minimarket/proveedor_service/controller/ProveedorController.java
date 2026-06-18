package com.minimarket.proveedor_service.controller;

import com.minimarket.proveedor_service.dto.ProveedorRequestDTO;
import com.minimarket.proveedor_service.dto.ProveedorResponseDTO;
import com.minimarket.proveedor_service.exception.ProveedorNotFoundException;
import com.minimarket.proveedor_service.service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(
        name = "Proveedores",
        description = "Operaciones relacionadas con la gestión de proveedores"
)
@RestController
@RequestMapping("/api/v1/proveedores")
@RequiredArgsConstructor
@Slf4j
public class ProveedorController {

    private final ProveedorService proveedorService;

    @Operation(summary = "Listar todos los proveedores")
    @GetMapping
    public ResponseEntity<List<ProveedorResponseDTO>> listarProveedores() {
        return ResponseEntity.ok(proveedorService.obtenerTodos());
    }

    @Operation(summary = "Buscar proveedor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> obtenerPorId(@PathVariable Long id) {
        log.info("BUSCANDO PROVEEDOR CON ID: {}", id);
        return proveedorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ProveedorNotFoundException(id));
    }

    @Operation(summary = "Buscar proveedores por correo electrónico")
    @GetMapping("/correo/{correo}")
    public ResponseEntity<List<ProveedorResponseDTO>> obtenerPorEmail(@PathVariable String correo) {
        List<ProveedorResponseDTO> listaDto = proveedorService.obtenerPorCorreo(correo);

        if (!listaDto.isEmpty()) {
            return ResponseEntity.ok(listaDto);
        }

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Crear un proveedor")
    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> crearProveedor(
            @Valid @RequestBody ProveedorRequestDTO dto) {

        return ResponseEntity.status(201)
                .body(proveedorService.guardar(dto));
    }

    @Operation(summary = "Actualizar un proveedor")
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> actualizarProv(
            @PathVariable Long id,
            @Valid @RequestBody ProveedorRequestDTO dto) {

        return proveedorService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ProveedorNotFoundException(id));
    }

    @Operation(summary = "Eliminar un proveedor")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        proveedorService.eliminarProv(id);

        return ResponseEntity.ok(
                Map.of("MENSAJE", "PROVEEDOR ELIMINADO CORRECTAMENTE")
        );
    }

    @Operation(summary = "Buscar proveedor por RUT")
    @GetMapping("/rut/{rut}")
    public ResponseEntity<ProveedorResponseDTO> buscarPorRut(
            @PathVariable String rut) {

        return proveedorService.obtenerPorRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar proveedores por nombre")
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProveedorResponseDTO>> buscarPorNombre(
            @PathVariable String nombre) {

        List<ProveedorResponseDTO> listaDto =
                proveedorService.buscarPorNombre(nombre);

        if (!listaDto.isEmpty()) {
            return ResponseEntity.ok(listaDto);
        }

        return ResponseEntity.noContent().build();
    }
}