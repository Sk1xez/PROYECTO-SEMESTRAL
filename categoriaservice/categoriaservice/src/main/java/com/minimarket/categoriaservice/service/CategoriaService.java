package com.minimarket.categoriaservice.service;
import com.minimarket.categoriaservice.dto.CategoriaDTO;
import com.minimarket.categoriaservice.model.Categoria;
import com.minimarket.categoriaservice.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    // Solo haremos el método para buscar por ID, que es el que necesita el Producto
    public CategoriaDTO getCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        return dto;
    }
} //tengo el pene chico
