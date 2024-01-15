package com.weare4saken.random.values.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity(name = "category")
@Data
public class CategoryEntity {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;

}
