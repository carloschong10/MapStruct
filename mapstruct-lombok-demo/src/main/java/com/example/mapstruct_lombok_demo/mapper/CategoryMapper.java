package com.example.mapstruct_lombok_demo.mapper;

import com.example.mapstruct_lombok_demo.dto.CategoryDto;
import com.example.mapstruct_lombok_demo.entity.Category;
import com.example.mapstruct_lombok_demo.repository.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CategoryMapper { //se pone como clase abstracta y no como interface para que se mapee bien el valor de status de la entidad Category

    @Autowired
    private CategoryRepository categoryRepository;

    //si se llaman igual no es necesario utilizar @Mapping o @Mappings
    @Mappings({
            @Mapping(source = "id", target = "categoryId"),
            @Mapping(source = "name", target = "categoryName")
    })
    abstract CategoryDto toDTO(Category category);

//    @InheritInverseConfiguration
//    Category toEntity(CategoryDto categoryDto);

    Category toEntity(CategoryDto categoryDto) {//Mapeo Personalizado: este será el único que tenga implementación personalizada para el campo status
        if (categoryDto == null) return null;

        Category category = categoryRepository.findById(categoryDto.getCategoryId())
                .orElse(null);

        if (category == null) return null;

        category.setId(categoryDto.getCategoryId());
        category.setName(categoryDto.getCategoryName());

        return category;
    }

    abstract List<CategoryDto> toDTOList(List<Category> categoryList);

    abstract List<Category> toEntityList(List<CategoryDto> categoryDtoList);
}
