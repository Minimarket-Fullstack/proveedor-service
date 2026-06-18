package com.minimarket.proveedor_service.controller;

import com.minimarket.proveedor_service.assembler.ProveedorModelAssembler;
import com.minimarket.proveedor_service.dto.ProveedorRequestDTO;
import com.minimarket.proveedor_service.dto.ProveedorResponseDTO;
import com.minimarket.proveedor_service.exception.ProveedorNotFoundException;
import com.minimarket.proveedor_service.service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/proveedores")
@RequiredArgsConstructor
@Tag(name = "Proveedores V2 - HATEOAS", description = "CRUD de proveedores con enlaces HATEOAS")
public class ProveedorControllerV2 {

    private final ProveedorService proveedorService;
    private final ProveedorModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar proveedores con HATEOAS")
    public CollectionModel<EntityModel<ProveedorResponseDTO>> listarProveedores() {
        List<EntityModel<ProveedorResponseDTO>> proveedores = proveedorService.obtenerTodos()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(proveedores,
                linkTo(methodOn(ProveedorControllerV2.class).listarProveedores()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar proveedor por ID con HATEOAS")
    public EntityModel<ProveedorResponseDTO> obtenerPorId(@PathVariable Long id) {
        ProveedorResponseDTO proveedor = proveedorService.obtenerPorId(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));
        return assembler.toModel(proveedor);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear proveedor con HATEOAS")
    public ResponseEntity<EntityModel<ProveedorResponseDTO>> crearProveedor(@Valid @RequestBody ProveedorRequestDTO dto) {
        ProveedorResponseDTO proveedor = proveedorService.guardar(dto);
        return ResponseEntity.status(201).body(assembler.toModel(proveedor));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar proveedor con HATEOAS")
    public EntityModel<ProveedorResponseDTO> actualizarProveedor(@PathVariable Long id,
                                                                 @Valid @RequestBody ProveedorRequestDTO dto) {
        ProveedorResponseDTO proveedor = proveedorService.actualizar(id, dto)
                .orElseThrow(() -> new ProveedorNotFoundException(id));
        return assembler.toModel(proveedor);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar proveedor")
    public ResponseEntity<Map<String, String>> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProv(id);
        return ResponseEntity.ok(Map.of("mensaje", "Proveedor eliminado correctamente"));
    }

    @GetMapping(value = "/rut/{rut}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar proveedor por RUT con HATEOAS")
    public EntityModel<ProveedorResponseDTO> buscarPorRut(@PathVariable String rut) {
        ProveedorResponseDTO proveedor = proveedorService.obtenerPorRut(rut)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con RUT: " + rut));
        return assembler.toModel(proveedor);
    }

    @GetMapping(value = "/nombre/{nombre}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar proveedores por nombre con HATEOAS")
    public CollectionModel<EntityModel<ProveedorResponseDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<EntityModel<ProveedorResponseDTO>> proveedores = proveedorService.buscarPorNombre(nombre)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(proveedores,
                linkTo(methodOn(ProveedorControllerV2.class).buscarPorNombre(nombre)).withSelfRel(),
                linkTo(methodOn(ProveedorControllerV2.class).listarProveedores()).withRel("proveedores"));
    }
}
