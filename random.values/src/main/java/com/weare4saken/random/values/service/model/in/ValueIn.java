package com.weare4saken.random.values.service.model.in;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class CategoryIn {
    private UUID id;
    private String name;
    private String description;
}
