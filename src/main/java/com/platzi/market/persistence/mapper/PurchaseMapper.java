package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Purchase;
import com.platzi.market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

        //Mapeamos el entity Purchase a PurchaseItem (Variables de esp a ingles)
        @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items")
        })
        Purchase toPurchase(Compra compra);
        List<Purchase> toPurchases(List<Compra> compras);
        //No es necesario los Mappings de la lista de Purchase en plural, ya est[a en singular.

        //La clase destino SIEMPRE debe tener todos los mapeos,
        //De lo contrario los ignoramos explicitamente
        @InheritInverseConfiguration
        @Mappings({ //Ignoramos los atributos de ComprasProducto que no queremos mapear
                @Mapping(target = "cliente", ignore = true),
        })
        Compra toCompra(Purchase purchase);
}
