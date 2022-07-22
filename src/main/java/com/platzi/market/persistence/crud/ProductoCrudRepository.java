package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Long> {
    //Tambien soporta consultas del tipo SQL pero es mejor practica un QueryMethod
   // @Query(value = "SELECT * FROM productos WHERE nombre LIKE %?1%", nativeQuery = true)

    //respetar el camelCase para el QueryMethod
    List<Producto> findByIdCategoriaOrderByNombreAsc(Long idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado
                                    (int cantidadStock, boolean estado);

    //    Esto lo hacemos con findById que proporciona crudRepository
    //    Optional<Producto> findByIdProducto(Long idProducto);


}
