package com.weare4saken.random.values.service.model.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class CategoryOut {

    private UUID id;
    private String name;
    private String description;
    private List<ValueOut> values;

}
