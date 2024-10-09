package com.example.mapstruct_lombok_demo.mapper;

import com.example.mapstruct_lombok_demo.dto.CategoryDto;
import com.example.mapstruct_lombok_demo.entity.Category;
import com.example.mapstruct_lombok_demo.repository.CategoryRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Mapper()
public interface CategoryMapper { //se pone como clase abstracta y no como interface para que se mapee bien el valor de status de la entidad Category

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

//    @Autowired
//    private CategoryRepository categoryRepository;

    //si se llaman igual no es necesario utilizar @Mapping o @Mappings
    @Mappings({
            @Mapping(source = "id", target = "categoryId"),
            @Mapping(source = "name", target = "categoryName")
    })
    CategoryDto toDTO(Category category);

//    @InheritInverseConfiguration
//    Category toEntity(CategoryDto categoryDto);
    @InheritInverseConfiguration
    Category toEntity(CategoryDto categoryDto);

    List<CategoryDto> toDTOList(List<Category> categoryList);

    List<Category> toEntityList(List<CategoryDto> categoryDtoList);
}
