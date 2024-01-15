package com.weare4saken.random.values.rest;

import com.weare4saken.random.values.rest.converter.RandomsConverters;
import com.weare4saken.random.values.rest.model.in.CategoryRestIn;
import com.weare4saken.random.values.rest.model.out.CategoryRestOut;
import com.weare4saken.random.values.service.RandomValuesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/1/random/category")
@RequiredArgsConstructor
public class CategoryController implements RandomsConverters {

    private final RandomValuesService randomValuesService;

    @GetMapping
    List<CategoryRestOut> getAllCategories() {
        return randomValuesService.getAllCategories().stream()
                .map(this::convert)
                .toList();
    }

    @PostMapping("/add")
    CategoryRestOut addNewCategory(@RequestBody CategoryRestIn categoryRestIn) {
        return convert(
                randomValuesService.addNewCategory(
                        convert(categoryRestIn)
                )
        );
    }

    @PutMapping("/update/{categoryId}")
    CategoryRestOut updateCategory(@RequestBody CategoryRestIn categoryRestIn, @PathVariable UUID categoryId) {
        return convert(
                randomValuesService.updateCategory(
                        convert(categoryRestIn, categoryId)
                )
        );
    }

    @DeleteMapping("/delete/{categoryId}")
    void deleteCategory(@PathVariable UUID categoryId) {
        randomValuesService.deleteCategory(categoryId);
    }

}
