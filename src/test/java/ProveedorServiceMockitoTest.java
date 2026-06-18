import com.minimarket.proveedor_service.dto.ProveedorRequestDTO;
import com.minimarket.proveedor_service.dto.ProveedorResponseDTO;
import com.minimarket.proveedor_service.model.Proveedor;
import com.minimarket.proveedor_service.repository.ProveedorRepository;
import com.minimarket.proveedor_service.service.ProveedorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceMockitoTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void obtenerTodos_deberiaRetornarProveedoresActivos() {
        Proveedor proveedor = new Proveedor(1L, "12.345.678-9", "Distribuidora Central", "contacto@central.cl", "+56911111111", "Santiago", true);
        when(proveedorRepository.findByActivoTrue()).thenReturn(List.of(proveedor));

        List<ProveedorResponseDTO> resultado = proveedorService.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals("Distribuidora Central", resultado.get(0).getNombre());
        verify(proveedorRepository).findByActivoTrue();
    }

    @Test
    void guardar_deberiaGuardarProveedor() {
        ProveedorRequestDTO dto = new ProveedorRequestDTO("12.345.678-9", "Distribuidora Central", "contacto@central.cl", "+56911111111", "Santiago");
        Proveedor guardado = new Proveedor(1L, dto.getRut(), dto.getNombre(), dto.getEmail(), dto.getTelefono(), dto.getDireccion(), true);
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(guardado);

        ProveedorResponseDTO resultado = proveedorService.guardar(dto);

        assertEquals(1L, resultado.getId());
        assertEquals("Distribuidora Central", resultado.getNombre());
        verify(proveedorRepository).save(any(Proveedor.class));
    }

    @Test
    void obtenerPorId_deberiaRetornarProveedorActivo() {
        Proveedor proveedor = new Proveedor(1L, "12.345.678-9", "Distribuidora Central", "contacto@central.cl", "+56911111111", "Santiago", true);
        when(proveedorRepository.findByIdAndActivoTrue(1L)).thenReturn(Optional.of(proveedor));

        Optional<ProveedorResponseDTO> resultado = proveedorService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Distribuidora Central", resultado.get().getNombre());
        verify(proveedorRepository).findByIdAndActivoTrue(1L);
    }

@Test
    void obtenerPorId_deberiaRetornarVacioSiProveedorNoExiste() {
        when(proveedorRepository.findByIdAndActivoTrue(99L)).thenReturn(Optional.empty());

        Optional<ProveedorResponseDTO> resultado = proveedorService.obtenerPorId(99L);

        assertTrue(resultado.isEmpty());
        verify(proveedorRepository).findByIdAndActivoTrue(99L);
    }

    @Test
    void actualizar_deberiaModificarProveedorActivo() {
        Proveedor proveedor = new Proveedor(1L, "12.345.678-9", "Distribuidora Central", "contacto@central.cl", "+56911111111", "Santiago", true);
        ProveedorRequestDTO dto = new ProveedorRequestDTO("12.345.678-9", "Nuevo Proveedor", "nuevo@test.cl", "+56922222222", "Valparaiso");
        when(proveedorRepository.findByIdAndActivoTrue(1L)).thenReturn(Optional.of(proveedor));
        when(proveedorRepository.save(any(Proveedor.class))).thenAnswer(inv -> inv.getArgument(0));

        Optional<ProveedorResponseDTO> resultado = proveedorService.actualizar(1L, dto);

        assertTrue(resultado.isPresent());
        assertEquals("Nuevo Proveedor", resultado.get().getNombre());
        assertEquals("nuevo@test.cl", resultado.get().getEmail());
        verify(proveedorRepository).save(proveedor);
    }

    @Test
    void eliminarProv_deberiaDesactivarProveedor() {
        Proveedor proveedor = new Proveedor(1L, "12.345.678-9", "Distribuidora Central", "contacto@central.cl", "+56911111111", "Santiago", true);
        when(proveedorRepository.findByIdAndActivoTrue(1L)).thenReturn(Optional.of(proveedor));

        proveedorService.eliminarProv(1L);

        assertFalse(proveedor.isActivo());
        verify(proveedorRepository).save(proveedor);
    }

    @Test
    void buscarPorNombre_deberiaRetornarProveedoresActivos() {
        Proveedor proveedor = new Proveedor(1L, "12.345.678-9", "Distribuidora Central", "contacto@central.cl", "+56911111111", "Santiago", true);
        when(proveedorRepository.findByNombreContainingIgnoreCaseAndActivoTrue("central")).thenReturn(List.of(proveedor));

        List<ProveedorResponseDTO> resultado = proveedorService.buscarPorNombre("central");

        assertEquals(1, resultado.size());
        assertEquals("Distribuidora Central", resultado.get(0).getNombre());
        verify(proveedorRepository).findByNombreContainingIgnoreCaseAndActivoTrue("central");
    }
}
