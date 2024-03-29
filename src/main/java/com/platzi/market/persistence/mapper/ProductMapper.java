package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Product;
import com.platzi.market.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

//el uses es para que mapee categoria a Category, ya que tiene su propio Mapper
@Mapper( componentModel = "spring", uses = {CategoryMapper.class} )
public interface ProductMapper {

    //Mapeamos el entity Producto a Product (Variables de esp a ingles)
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
//            @Mapping(source = "codigoBarras", target = "barcode"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category")
    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos);

    //Conversion contraria, la anotacion InheritInverseConfiguration
    //Nos ahorra hacer los Mappings de arriba para esta conversion contraria
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "codigoBarras", ignore = true)
    })
    Producto toProducto(Product product);
    List<Producto> toProductos(List<Product> products);
}
