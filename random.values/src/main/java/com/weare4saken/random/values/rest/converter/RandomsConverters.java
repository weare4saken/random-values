package com.weare4saken.random.values.rest;

import com.weare4saken.random.values.rest.model.in.CategoryRestIn;
import com.weare4saken.random.values.rest.model.out.CategoryRestOut;
import com.weare4saken.random.values.service.model.in.CategoryIn;
import com.weare4saken.random.values.service.model.out.CategoryOut;

import java.util.UUID;

public interface RandomsConverters {

    private CategoryRestOut convert(CategoryOut out) {
        return CategoryRestOut.builder()
                .id(out.getId())
                .name(out.getName())
                .description((out.getDescription()))
                .values(out.getValues())
                .build();
    }

    private CategoryIn convert(CategoryRestIn restIn) {
        return CategoryIn.builder()
                .name(restIn.getName())
                .description((restIn.getDescription()))
                .build();
    }

    private CategoryIn convert(CategoryRestIn restIn, UUID categoryId) {
        return CategoryIn.builder()
                .id(categoryId)
                .name(restIn.getName())
                .description((restIn.getDescription()))
                .build();
    }
}
