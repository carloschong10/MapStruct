package com.example.mapstruct_lombok_demo.mapper;

import com.example.mapstruct_lombok_demo.dto.ProductDto;
import com.example.mapstruct_lombok_demo.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CategoryMapper.class) //le indico que va a hacer uso del mapeador de Category para que me lo inyecte en la clase ProductMapperImpl y no se genere el método protegido categoryDtoToCategory
@Mapper(uses = CategoryMapper.class) //le indico que va a hacer uso del mapeador de Category para que me lo inyecte en la clase ProductMapperImpl y no se genere el método protegido categoryDtoToCategory
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            //no es necesario mapear los atributos que se llaman igual
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "name", target = "name"),
            @Mapping(source = "creationDate", target = "creationDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "name", target = "productName"),
            @Mapping(source = "id", target = "productId"),
            @Mapping(source = "category", target = "productCategory"),
            @Mapping(source = "price", target = "price", numberFormat = "$0.00"),
            //si no tuviera el uses del CategoryMapper iria como lo siguiente
//            @Mapping(source = "category.id", target = "productCategory.categoryId"),
//            @Mapping(source = "category.name", target = "productCategory.categoryName")
    })
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "creationDate", target = "creationDate")
    ProductDto toDTO(Product product);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "creationDate", ignore = true) //para ignorar una propiedad en este caso del entity, y en este caso ya no mapeará el campo creationDate
    })
    Product toEntity(ProductDto productDto);

    List<ProductDto> toDTOProductList(List<Product> productList); //esto funcionará automaticamente si existe un método que mapee Un entity a un DTO (ProductDto)

    List<Product> toEntityList(List<ProductDto> productDtoList); //esto funcionará automaticamente si existe un método que mapee un DTO (ProductDto) a un entity (toEntity)
}
