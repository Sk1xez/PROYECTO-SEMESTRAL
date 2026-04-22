package com.system_mk.system_mk.cliente;

import com.system_mk.system_mk.dto.CategoriaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient le dice a Spring: "Conéctate al microservicio en el puerto 8082"
@FeignCliente(name = "categoria-service", url = "http://localhost:8082/api/categorias")
public interface CategoriaCliente {

    // Aquí definimos la firma del método que queremos consumir del otro servicio
    // Debe ser exactamente igual a la ruta del controlador de Categoria
    @GetMapping("/{id}")
    CategoriaDTO getCategoriaById(@PathVariable("id") Long id);
}
