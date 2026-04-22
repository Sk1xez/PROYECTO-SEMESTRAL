package com.system_mk.system_mk.controller;

import com.system_mk.system_mk.dto.ProductoDTO;
import com.system_mk.system_mk.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase expone rutas web (Endpoints) y que responderá con datos JSON.
@RequestMapping("/api/productos") // Define la URL base. Todas las rutas de abajo empezarán con "localhost:8081/api/productos".
public class ProductoController {

    @Autowired // Inyectamos el servicio para poder usar sus métodos de lógica de negocio.
    private ProductoService productoService;

    // --- Endpoint GET (Listar) ---
    @GetMapping // Responde a peticiones HTTP GET.
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        // Llama al servicio y devuelve la lista con un código HTTP 200 (OK).
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    // --- Endpoint POST (Crear) ---
    @PostMapping // Responde a peticiones HTTP POST.
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
        // @RequestBody agarra el JSON que envía React y lo transforma mágicamente en un objeto ProductoDTO.
        ProductoDTO createdProducto = productoService.createProducto(productoDTO);
        // Devuelve el producto creado con un código HTTP 201 (CREATED).
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProducto);
    }

    // --- Endpoint PUT (Actualizar) ---
    @PutMapping("/{id}") // Responde a peticiones HTTP PUT en la ruta "/api/productos/{id}"
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        // @PathVariable saca el número de la URL (ej: /api/productos/5 -> id = 5).
        // @RequestBody saca los nuevos datos del JSON.
        ProductoDTO updatedProducto = productoService.updateProducto(id, productoDTO);
        return ResponseEntity.ok(updatedProducto); // Devuelve HTTP 200 (OK).
    }

    // --- Endpoint DELETE (Eliminar) ---
    @DeleteMapping("/{id}") // Responde a peticiones HTTP DELETE en la ruta "/api/productos/{id}"
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        // Llama al método de eliminar en el servicio.
        productoService.deleteProducto(id);
        // Devuelve HTTP 204 (NO CONTENT) porque se borró con éxito y ya no hay datos que devolver.
        return ResponseEntity.noContent().build();
    }
}