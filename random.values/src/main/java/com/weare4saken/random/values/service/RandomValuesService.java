package com.weare4saken.random.values.service;

import com.weare4saken.random.values.service.model.in.CategoryIn;
import com.weare4saken.random.values.service.model.in.ValueIn;
import com.weare4saken.random.values.service.model.out.CategoryOut;
import com.weare4saken.random.values.service.model.out.ValueOut;

import java.util.List;
import java.util.UUID;

public interface RandomValuesService {

    List<CategoryOut> getAllCategories();
    CategoryOut addNewCategory(CategoryIn categoryIn);
    CategoryOut updateCategory(CategoryIn categoryIn);
    void deleteCategory(UUID categoryId);
    ValueOut getValueByCategoryId(UUID categoryId);
    ValueOut addNewValue(ValueIn valueIn);
    ValueOut updateValue(ValueIn valueIn);
    void deleteValue(UUID categoryId);

}
