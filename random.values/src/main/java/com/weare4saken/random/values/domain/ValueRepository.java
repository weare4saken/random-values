package com.weare4saken.random.values.domain;

import com.weare4saken.random.values.domain.entity.ValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ValueRepository extends JpaRepository<ValueEntity, UUID> {

    List<ValueEntity> findValueEntitiesByCategoryId(UUID categoryId);

}
