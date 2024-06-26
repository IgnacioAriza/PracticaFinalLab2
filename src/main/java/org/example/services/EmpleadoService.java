package org.example.services;

import org.example.entities.Empleado;
import org.example.repositories.EmpleadoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class EmpleadoService extends BaseService<Empleado,Long> {
    public EmpleadoService(EmpleadoRepository empleadoRepository){
        super(empleadoRepository);
    }

}
