package com.system_mk.system_mk.dto;

import lombok.Data;

@Data // Nuevamente, Lombok nos genera Getters y Setters para esta clase.
public class ProductoDTO {
    // El DTO (Data Transfer Object) es un objeto plano.
    // No tiene anotaciones de base de datos porque su única misión es transportar datos
    // entre el Frontend (React) y el Backend (Spring Boot) de forma segura.
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
    private Long categoriaId;
    private String categoriaNombre;
}