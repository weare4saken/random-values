package com.weare4saken.random.values.rest.model.out;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public class CategoryRestOut {

    private UUID id;
    private String name;
    private String description;
    private List<ValueRestOut> values;

}
