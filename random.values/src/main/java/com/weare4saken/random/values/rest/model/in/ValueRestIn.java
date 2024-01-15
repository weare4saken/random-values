package com.weare4saken.random.values.rest.model.in;

import lombok.Data;

import java.util.UUID;

@Data
public class ValueRestIn {

    private String name;
    private String description;
    private UUID categoryId;

}
