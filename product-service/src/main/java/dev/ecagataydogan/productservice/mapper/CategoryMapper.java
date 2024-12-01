package dev.ecagataydogan.productservice.mapper;

import dev.ecagataydogan.productservice.dto.response.CategoryResponse;
import dev.ecagataydogan.productservice.entity.Category;

public class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
