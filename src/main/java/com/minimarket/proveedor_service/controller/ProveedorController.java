package com.minimarket.proveedor_service.controller;

import com.minimarket.proveedor_service.dto.ProveedorRequestDTO;
import com.minimarket.proveedor_service.dto.ProveedorResponseDTO;
import com.minimarket.proveedor_service.service.ProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorResponseDTO>> listarProveedores() {
        return ResponseEntity.ok(proveedorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> obtenerPorId(@PathVariable Long id) {
        return proveedorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> crearProveedor(@Valid @RequestBody ProveedorRequestDTO dto) {
        return ResponseEntity.status(201).body(proveedorService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> actualizarProv(@PathVariable Long id, @Valid @RequestBody ProveedorRequestDTO dto) {
        return proveedorService.actualizar(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.eliminarProv(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<ProveedorResponseDTO> buscarPorRut(@PathVariable String rut) {
        return proveedorService.obtenerPorRut(rut).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProveedorResponseDTO>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(proveedorService.buscarPorNombre(nombre));
    }
}