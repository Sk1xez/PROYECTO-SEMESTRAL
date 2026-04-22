package com.system_mk.system_mk.service;

import com.system_mk.system_mk.dto.CategoriaDTO;
import com.system_mk.system_mk.cliente.CategoriaCliente;
import com.system_mk.system_mk.model.Producto;
import com.system_mk.system_mk.dto.ProductoDTO;
import com.system_mk.system_mk.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Marca esta clase como un Servicio. Aquí va toda la "lógica de negocio".
public class ProductoService {

    @Autowired // Repositorio para la base de datos local
    private ProductoRepository productoRepository;

    @Autowired // ¡AQUÍ VA EL CLIENTE FEIGN! (Debe estar dentro de la clase)
    private CategoriaCliente categoriaCliente;

    // --- GET: Obtener todos ---
    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // --- POST: Crear un nuevo producto ---
    public ProductoDTO createProducto(ProductoDTO productoDTO) {
        Producto producto = convertToEntity(productoDTO);
        Producto savedProducto = productoRepository.save(producto);
        return convertToDTO(savedProducto);
    }

    // --- PUT: Actualizar un producto existente ---
    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {
        Producto existingProducto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));

        existingProducto.setName(productoDTO.getName());
        existingProducto.setPrice(productoDTO.getPrice());
        existingProducto.setStock(productoDTO.getStock());
        existingProducto.setCategoriaId(productoDTO.getCategoriaId());

        Producto updatedProducto = productoRepository.save(existingProducto);
        return convertToDTO(updatedProducto);
    }

    // --- DELETE: Eliminar un producto ---
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // ==========================================
    // MÉTODOS AUXILIARES (Para transformar datos)
    // ==========================================

    // ESTE ES EL ÚNICO MÉTODO convertToDTO (Reemplaza al anterior)
    private ProductoDTO convertToDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setName(producto.getName());
        dto.setPrice(producto.getPrice());
        dto.setStock(producto.getStock());
        dto.setCategoriaId(producto.getCategoriaId());

        // --- ¡MAGIA DE MICROSERVICIOS CON FEIGN! ---
        try {
            // Llamamos al microservicio de Categorías (puerto 8082)
            CategoriaDTO categoria = categoriaCliente.getCategoriaById(producto.getCategoriaId());
            // Si responde bien, sacamos el nombre y lo agregamos al DTO
            dto.setCategoriaNombre(categoria.getNombre());
        } catch (Exception e) {
            // Si el otro servicio está apagado o falla, manejamos el error sin que la app se caiga
            dto.setCategoriaNombre("Categoría no disponible");
        }

        return dto;
    }

    // Convierte de DTO (Frontend) a Entidad (BD)
    private Producto convertToEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setName(dto.getName());
        producto.setPrice(dto.getPrice());
        producto.setStock(dto.getStock());
        producto.setCategoriaId(dto.getCategoriaId());
        return producto;
    }
} // <--- FIN DE LA CLASE. ¡Nada debe ir debajo de esta llave!}