package com.weare4saken.random.values.service.impl;

import com.weare4saken.random.values.domain.CategoryRepository;
import com.weare4saken.random.values.domain.ValueRepository;
import com.weare4saken.random.values.domain.entity.CategoryEntity;
import com.weare4saken.random.values.domain.entity.ValueEntity;
import com.weare4saken.random.values.service.RandomValuesService;
import com.weare4saken.random.values.service.model.in.CategoryIn;
import com.weare4saken.random.values.service.model.in.ValueIn;
import com.weare4saken.random.values.service.model.out.CategoryOut;
import com.weare4saken.random.values.service.model.out.ValueOut;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Log4j
public class RandomValuesServiceImpl implements RandomValuesService {

    private final CategoryRepository categoryRepository;
    private final ValueRepository valueRepository;

    @Override
    public List<CategoryOut> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convert)
                .toList();
    }

    @Override
    public CategoryOut addNewCategory(CategoryIn categoryIn) {
        return convert(
                categoryRepository.save(
                        convert(categoryIn)
                )
        );
    }

    @Override
    public CategoryOut updateCategory(CategoryIn categoryIn) {
        return categoryRepository.findById(categoryIn.getId())
                .map(category -> {
                    category.setDescription(categoryIn.getDescription());
                    category.setName(categoryIn.getName());

                    return category;
                })
                .map(categoryRepository::save)
                .map(this::convert)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteCategory(UUID categoryId) {
        var valueEntites = valueRepository.findValueEntitiesByCategoryId(categoryId);
        valueRepository.deleteAll(valueEntites);

        categoryRepository.deleteById(categoryId);
    }

    @Override
    public ValueOut getValueByCategoryId(UUID categoryId) {
        var values = valueRepository.findValueEntitiesByCategoryId(categoryId);
        var index = getRandomIndex(values);

        return convert(
                values.get(index)
        );
    }

    @Override
    public ValueOut addNewValue(ValueIn valueIn) {
        return categoryRepository.findById(valueIn.getId())
                .map(category ->
                                valueRepository.save(
                                        convert(valueIn, category)
                                )
                )
                .map(this::convert)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ValueOut updateValue(ValueIn valueIn) {
        return valueRepository.findById(valueIn.getId())
                .map(category -> {
                    category.setDescription(valueIn.getDescription());
                    category.setName(valueIn.getName());

                    return category;
                })
                .map(valueRepository::save)
                .map(this::convert)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteValue(UUID valueId) {
        valueRepository.deleteById(valueId);
    }

    private int getRandomIndex(List<?> list) {
        return ThreadLocalRandom.current().nextInt(0, list.size() - 1);
    }

    private CategoryOut convert(CategoryEntity categoryEntity) {
        return CategoryOut.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .values(
                        categoryEntity.getValues().stream()
                                .map(this::convert)
                                .toList()
                )
                .build();
    }

    private CategoryEntity convert(CategoryIn categoryIn) {
        return CategoryEntity.builder()
                .id(Objects.isNull(categoryIn.getId()) ? UUID.randomUUID() : categoryIn.getId())
                .name(categoryIn.getName())
                .description(categoryIn.getDescription())
                .build();
    }

    private ValueOut convert(ValueEntity valueEntity) {
        return ValueOut.builder()
                .id(valueEntity.getId())
                .name(valueEntity.getName())
                .description(valueEntity.getDescription())
                .categoryId(valueEntity.getCategory().getId())
                .build();
    }

    private ValueEntity convert(ValueIn valueIn, CategoryEntity categoryEntity) {
        return ValueEntity.builder()
                .id(Objects.isNull(valueIn.getId()) ? UUID.randomUUID() : valueIn.getId())
                .name(valueIn.getName())
                .description(valueIn.getDescription())
                .category(categoryEntity)
                .build();
    }

}
