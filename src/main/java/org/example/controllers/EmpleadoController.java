package org.example.controllers;

import org.example.entities.Empleado;
import org.example.services.EmpleadoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController extends BaseController<Empleado,Long>{
    public EmpleadoController(EmpleadoService empleadoService){
        super(empleadoService);
    }
}