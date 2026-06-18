package com.minimarket.proveedor_service.assembler;

import com.minimarket.proveedor_service.controller.ProveedorControllerV2;
import com.minimarket.proveedor_service.dto.ProveedorResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<ProveedorResponseDTO, EntityModel<ProveedorResponseDTO>> {

    @Override
    public EntityModel<ProveedorResponseDTO> toModel(ProveedorResponseDTO proveedor) {

        return EntityModel.of(
                proveedor,

                linkTo(methodOn(ProveedorControllerV2.class)
                        .obtenerPorId(proveedor.getId()))
                        .withSelfRel(),

                linkTo(methodOn(ProveedorControllerV2.class)
                        .listarProveedores())
                        .withRel("proveedores")
        );
    }
}