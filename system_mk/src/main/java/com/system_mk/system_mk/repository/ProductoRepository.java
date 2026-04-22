package com.system_mk.system_mk.repository;

import com.system_mk.system_mk.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Le dice a Spring: "Este es un componente que accede a la base de datos".
// JpaRepository recibe dos cosas <La Entidad, El tipo de dato del ID (Long)>
// Solo con hacer esto, Spring ya nos regala métodos como save(), findAll(), findById(), deleteById().
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
