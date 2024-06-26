package org.example.repositories;

import org.example.entities.Empleado;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends BaseRepository<Empleado, Long> {
    List<Empleado> findByNombre(String s);
}
