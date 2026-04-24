package com.minimarket.categoriaservice.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias") // Crea la tabla "categorias" en Laragon
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre; // Ej: "Abarrotes", "Lácteos"
}
