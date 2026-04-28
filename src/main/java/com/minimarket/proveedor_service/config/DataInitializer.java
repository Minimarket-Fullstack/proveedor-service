package com.minimarket.proveedor_service.config;

import com.minimarket.proveedor_service.model.Proveedor;
import com.minimarket.proveedor_service.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProveedorRepository proveedorRepository;

    @Override
    public void run(String... args) {
        if (proveedorRepository.count() > 0) {
            log.info(">>> DataInitializer: La BD ya tiene datos, se omite la carga inicial");
            return;
        }

        log.info(">>> DataInitializer: BD vacía detectada, insertando proveedores de prueba...");

        proveedorRepository.save(new Proveedor(null, "76543210-1", "Distribuidora San Cristóbal",    "contacto@dsancristobal.cl", "+56912345678", "Av. Principal 123, Santiago",    true));
        proveedorRepository.save(new Proveedor(null, "87654321-2", "Comercial Norte Grande Ltda.",   "ventas@nortegrande.cl",     "+56923456789", "Calle Comercio 456, Antofagasta",true));
        proveedorRepository.save(new Proveedor(null, "98765432-3", "Lácteos Los Andes SpA",          "info@losandeslacteos.cl",   "+56934567890", "Los Andes 789, Valparaíso",       true));
        proveedorRepository.save(new Proveedor(null, "11223344-5", "Distribuidora Bebidas Chile",    "pedidos@bebidaschile.cl",   "+56945678901", "Ruta 68 Km 10, Pudahuel",         false));
        proveedorRepository.save(new Proveedor(null, "22334455-6", "Snacks y Confites Central",      "snacks@confitescentral.cl", "+56956789012", "Parque Industrial 321, Rancagua", true));
        proveedorRepository.save(new Proveedor(null, "33445566-7", "Aseo Industrial Santiago SpA",   "contacto@aseosantiago.cl",  "+56967890123", "Av. Industrial 654, Maipú",       true));
        proveedorRepository.save(new Proveedor(null, "44556677-8", "Distribuidora Fríos y Congelados","ventas@friosycl.cl",       "+56978901234", "Camino Lo Boza 100, Pudahuel",    false));
        proveedorRepository.save(new Proveedor(null, "55667788-9", "Panificados San Bernardo Ltda.", "pan@psb.cl",               "+56989012345", "Artesanos 22, San Bernardo",      true));
        proveedorRepository.save(new Proveedor(null, "66778899-0", "Frutas y Verduras El Mercado",   "ventas@elmercado.cl",       "+56990123456", "Camino Rural 55, Buin",           true));
        proveedorRepository.save(new Proveedor(null, "77889900-1", "Importadora Pacífico Sur SpA",   "contacto@pacificosur.cl",   "+56901234567", "Puerto 88, Valparaíso",           false));

        log.info(">>> DataInitializer: Carga finalizada. {} proveedores insertados correctamente.", proveedorRepository.count());
    }
}