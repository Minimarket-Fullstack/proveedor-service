package com.minimarket.proveedor_service.service;

import com.minimarket.proveedor_service.model.Proveedor;
import com.minimarket.proveedor_service.dto.ProveedorRequestDTO;
import com.minimarket.proveedor_service.dto.ProveedorResponseDTO;
import com.minimarket.proveedor_service.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    private ProveedorResponseDTO mapToDTO(Proveedor proveedor) {
        return new ProveedorResponseDTO(
                proveedor.getId(),
                proveedor.getRut(),
                proveedor.getNombre(),
                proveedor.getEmail(),
                proveedor.getTelefono(),
                proveedor.getDireccion()
        );
    }

    public List<ProveedorResponseDTO> obtenerTodos() {
        return proveedorRepository.findByActivoTrue().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<ProveedorResponseDTO> obtenerPorId(Long id) {
        return proveedorRepository.findById(id).map(this::mapToDTO);
    }

    public ProveedorResponseDTO guardar(ProveedorRequestDTO dto) {
        Proveedor proveedor = new Proveedor(null, dto.getRut(), dto.getNombre(), dto.getEmail(), dto.getTelefono(), dto.getDireccion(), true);
        return mapToDTO(proveedorRepository.save(proveedor));
    }

    public Optional<ProveedorResponseDTO> actualizar(Long id, ProveedorRequestDTO dto) {
        return proveedorRepository.findByIdAndActivoTrue(id).map(existente -> {
            existente.setNombre(dto.getNombre());
            existente.setEmail(dto.getEmail());
            existente.setTelefono(dto.getTelefono());
            existente.setDireccion(dto.getDireccion());
            return mapToDTO(proveedorRepository.save(existente));
        });
    }

    public void eliminarProv(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el id: " + id));
        proveedor.setActivo(false);
        proveedorRepository.save(proveedor);
    }

    public Optional<ProveedorResponseDTO> obtenerPorRut(String rut) {
        return proveedorRepository.findByRut(rut).map(this::mapToDTO);
    }

    public List<ProveedorResponseDTO> buscarPorNombre(String nombre) {
        return proveedorRepository.findByNombreContainingIgnoreCase(nombre).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<ProveedorResponseDTO> obtenerPorCorreo(String correo){
        return proveedorRepository.findByEmailContainingIgnoreCase(correo).map(this::mapToDTO);
    }
}