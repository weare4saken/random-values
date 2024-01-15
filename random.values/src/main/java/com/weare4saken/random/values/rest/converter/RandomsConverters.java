package com.weare4saken.random.values.rest.converter;

import com.weare4saken.random.values.rest.model.in.CategoryRestIn;
import com.weare4saken.random.values.rest.model.in.ValueRestIn;
import com.weare4saken.random.values.rest.model.out.CategoryRestOut;
import com.weare4saken.random.values.rest.model.out.ValueRestOut;
import com.weare4saken.random.values.service.model.in.CategoryIn;
import com.weare4saken.random.values.service.model.in.ValueIn;
import com.weare4saken.random.values.service.model.out.CategoryOut;
import com.weare4saken.random.values.service.model.out.ValueOut;

import java.util.UUID;

public interface RandomsConverters {

    default CategoryRestOut convert(CategoryOut out) {
        return CategoryRestOut.builder()
                .id(out.getId())
                .name(out.getName())
                .description((out.getDescription()))
                .values(
                        out.getValues().stream()
                                .map(this::convert)
                                .toList())
                .build();
    }

    default CategoryIn convert(CategoryRestIn restIn) {
        return CategoryIn.builder()
                .name(restIn.getName())
                .description((restIn.getDescription()))
                .build();
    }

    default CategoryIn convert(CategoryRestIn restIn, UUID categoryId) {
        return CategoryIn.builder()
                .id(categoryId)
                .name(restIn.getName())
                .description((restIn.getDescription()))
                .build();
    }

    default ValueRestOut convert(ValueOut out) {
        return ValueRestOut.builder()
                .id(out.getId())
                .name(out.getName())
                .description((out.getDescription()))
                .categoryId(out.getCategoryId())
                .build();
    }

    default ValueIn convert(ValueRestIn restIn) {
        return ValueIn.builder()
                .name(restIn.getName())
                .description((restIn.getDescription()))
                .categoryId(restIn.getCategoryId())
                .build();
    }

    default ValueIn convert(ValueRestIn restIn, UUID valueId) {
        return ValueIn.builder()
                .id(valueId)
                .name(restIn.getName())
                .description((restIn.getDescription()))
                .categoryId(restIn.getCategoryId())
                .build();
    }

}
