package com.example.mapstruct_lombok_demo.dto;

import com.example.mapstruct_lombok_demo.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
//    private Long id;
//    private String name;
    private Long productId;
    private String productName;
    private String creationDate;
    private String price;
    private CategoryDto productCategory; //tiene que tener el mismo nombre que la propiedad de Product (category), sinoMapStruct no lo genera bien

    /*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
    */
}
