package com.example.mapstruct_lombok_demo.config;

import com.example.mapstruct_lombok_demo.repository.ProductRepository;
import com.example.mapstruct_lombok_demo.dto.ProductDto;
import com.example.mapstruct_lombok_demo.entity.Product;
import com.example.mapstruct_lombok_demo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InitDatabase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Bean
    public CommandLineRunner testProductMapperCommand() {

        return args -> {
            List<Product> products = productRepository.findAll();

            System.out.println("PRODUCTS: ");
            products.forEach(System.out::println);

            System.out.println("PRODUCTS DTO: ");
//            List<ProductDto> productDtos = products
//                    .stream()
//                    .map(productMapper::toGetDTO)
//                    .peek(System.out::println)
//                    .toList();

            List<ProductDto> productDtos = productMapper.toDTOProductList(products);
            productDtos.forEach(System.out::println);

            System.out.println("MAPPED PRODUCTS: ");
            List<Product> productList = productMapper.toEntityList(productDtos);
            productList.forEach(System.out::println);


        };
    }
}
