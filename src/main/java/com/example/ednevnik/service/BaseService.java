package com.example.ednevnik.service;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseService {

    protected final ModelMapper modelMapper;

    public BaseService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Maps list of entities to list od DTOs.
     *
     * @param entities entities to map
     * @param dtoClass type of class to map to (DTO class!)
     * @param <T>      return type of DTO
     * @return list of mapped DTOs
     */
    public <T> List<T> mapEntitiesToDTO(List<?> entities, Class<T> dtoClass) {
        return entities.stream().map(entity -> mapEntityToDTO(entity, dtoClass)).collect(Collectors.toList());
    }

    public <T, W> W mapEntityToDTO(T entity, Class<W> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
