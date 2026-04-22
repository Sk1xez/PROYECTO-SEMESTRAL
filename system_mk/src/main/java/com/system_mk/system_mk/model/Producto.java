package com.system_mk.system_mk.model;

import jakarta.persistence.*; // Importamos las herramientas de JPA para bases de datos
import lombok.Data; // Lombok nos ahorra escribir getters y setters a mano

@Data // Anotación de Lombok: Genera getters, setters, toString, etc. automáticamente.
@Entity // Le dice a Spring Boot: "Esta clase es una tabla en la base de datos".
@Table(name = "productos") // Nombra explícitamente la tabla en PostgreSQL como "products".
public class Producto {

    @Id // Indica que este campo es la Llave Primaria (Primary Key).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PostgreSQL generará el ID automáticamente (autoincrementable).
    private Long id;

    @Column(nullable = false) // Esta columna no puede estar vacía en la BD.
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    // Aquí guardamos el ID de la categoría (Para después comunicarnos con Category-Service)
    @Column(name = "categoria_id")
    private Long categoriaId;
}
