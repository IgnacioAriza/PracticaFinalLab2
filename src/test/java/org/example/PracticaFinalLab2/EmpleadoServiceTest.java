package org.example.PracticaFinalLab2;

import org.example.entities.Empleado;
import org.example.repositories.EmpleadoRepository;
import org.example.services.EmpleadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarEmpleado() {
        Empleado empleado = new Empleado("Jonathan");
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);

        Empleado empleadoGuardada = null;
        try {
            empleadoGuardada = empleadoService.crear(empleado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(empleadoGuardada).isNotNull();
        assertThat(empleadoGuardada.getNombre()).isEqualTo("Jonathan");
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    public void testObtenerEmpleadoPorId() {
        Empleado empleado = new Empleado("Jonathan");
        empleado.setId(1L);
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        Optional<Empleado> empleadoRecuperada = null;
        try {
            empleadoRecuperada = empleadoService.buscarPorId(1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(empleadoRecuperada).isPresent();
        assertThat(empleadoRecuperada.get().getNombre()).isEqualTo("Jonathan");
        verify(empleadoRepository, times(1)).findById(1L);
    }

    @Test
    public void testObtenerTodasLasEmpleadoes() {
        Empleado empleado1 = new Empleado("Jonathan");
        Empleado empleado2 = new Empleado("Martin");
        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(empleado1, empleado2));

        List<Empleado> empleadoes = null;
        try {
            empleadoes = empleadoService.listar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(empleadoes).hasSize(2);
        assertThat(empleadoes).extracting(Empleado::getNombre).contains("Jonathan", "Martin");
        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    public void testEliminarEmpleadoPorId() {
        try {
            empleadoService.eliminar(1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        verify(empleadoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGuardarEmpleado_LanzaExcepcion() {
        Empleado empleado = new Empleado("Jonathan");
        when(empleadoRepository.save(any(Empleado.class))).thenThrow(RuntimeException.class);

        try {
            empleadoService.crear(empleado);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(Exception.class);
        }

        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }
}