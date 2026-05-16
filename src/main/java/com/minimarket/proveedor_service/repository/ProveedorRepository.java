package com.minimarket.proveedor_service.repository;

import com.minimarket.proveedor_service.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//entidad y tipo del id
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    List<Proveedor> findByActivoTrue();

    Optional<Proveedor> findByIdAndActivoTrue(Long id);

    Optional<Proveedor> findByRutAndActivoTrue(String rut);

    List<Proveedor> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);
    //Lista?
    List<Proveedor> findByEmailContainingIgnoreCaseAndActivoTrue(String correo);

}
