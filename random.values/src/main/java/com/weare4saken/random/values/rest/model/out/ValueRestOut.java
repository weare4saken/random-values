package com.weare4saken.random.values.rest.model.out;

import lombok.Builder;

import java.util.UUID;

@Builder
public class ValueRestOut {

    private UUID id;
    private String name;
    private String description;
    private UUID categoryId;

}
