package org.example.PracticaFinalLab2;

import org.example.entities.Empleado;
import org.example.repositories.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class EmpleadoRepositoryTest {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Test
    public void testGuardarYRecuperarEmpleado() {
        Empleado empleado = new Empleado("Jonathan");
        Empleado empleadoGuardado = empleadoRepository.save(empleado);

        assertThat(empleadoGuardado.getId()).isNotNull();
        Empleado empleadoRecuperado = empleadoRepository.findById(empleadoGuardado.getId()).orElse(null);
        assertThat(empleadoRecuperado).isNotNull();
        assertThat(empleadoRecuperado.getNombre()).isEqualTo("Jonathan");

    }

    @Test
    public void testEncontrarTodosLasEmpleados() {
        Empleado empleado1 = new Empleado();
        empleado1.setNombre("Jonathan");
        Empleado empleado2 = new Empleado();
        empleado2.setNombre("Jonathan");
        Empleado empleado3 = new Empleado();
        empleado3.setNombre("Jonathan");
        empleadoRepository.save(empleado1);
        empleadoRepository.save(empleado2);
        empleadoRepository.save(empleado3);

        List<Empleado> empleados = empleadoRepository.findByNombre("Jonathan");
        assertThat(empleados).hasSize(3);
        assertThat(empleados).extracting(Empleado::getNombre).containsOnly("Jonathan");
    }
    @Test
    public void testActualizarEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setNombre("Jonathan");
        Empleado empleadoGuardada = empleadoRepository.save(empleado);

        empleadoGuardada.setNombre("Jonathan Actualizada");
        Empleado empleadoActualizada = empleadoRepository.save(empleadoGuardada);
        assertThat(empleadoActualizada.getNombre()).isEqualTo("Jonathan Actualizada");
    }
    @Test
    public void testEliminarEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setNombre("Empleado a eliminar");
        Empleado empleadoGuardada = empleadoRepository.save(empleado);

        empleadoRepository.deleteById(empleadoGuardada.getId());
        Empleado empleadoEliminada = empleadoRepository.findById(empleadoGuardada.getId()).orElse(null);
        assertThat(empleadoEliminada).isNull();
    }
    @Test
    public void testContarEmpleados() {
        Empleado empleado1 = new Empleado();
        empleado1.setNombre("Jonathan");
        Empleado empleado2 = new Empleado();
        empleado2.setNombre("Martin");
        empleadoRepository.save(empleado1);
        empleadoRepository.save(empleado2);
        long count = empleadoRepository.count();
        assertThat(count).isEqualTo(2);
    }

}
