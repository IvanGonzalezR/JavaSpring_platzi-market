package com.platzi.market.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //Mapeo de la clase Categoria a Category con MapStruct
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })

    Category toCategory(Categoria categoria);
    //Indicacion de que la inversa de este mapeo es la clase Categoria a Category con MapStruct
    //Es decir realiza los @Mappings pero a la inversa
    @InheritInverseConfiguration
    // En Categoria tenemos "productos" y en Category NO, entonces lo indicamos ->
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
