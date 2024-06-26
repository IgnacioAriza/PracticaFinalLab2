package org.example.services;

import org.example.entities.Empleado;
import org.example.entities.Base;
import org.example.repositories.BaseRepository;
import org.example.repositories.EmpleadoRepository;

import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseService<E extends Base, ID extends Serializable> {

    protected BaseRepository<E, ID> baseRepository;

    public BaseService(EmpleadoRepository baseRepository){
        this.baseRepository = (BaseRepository<E, ID>) baseRepository;
    }
    @Transactional

    public List<E> listar() throws Exception {
        try {
            return baseRepository.findAll();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Optional<E> buscarPorId(ID id) throws Exception {
        try {
            return Optional.ofNullable(baseRepository.findById(id).orElse(null));
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public E crear(E entity) throws Exception {
        try{
            return baseRepository.save(entity);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public E actualizar(E entity) throws Exception {
        try{
            return baseRepository.save(entity);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void eliminar(ID id) throws Exception {
        try{
            baseRepository.deleteById(id);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
