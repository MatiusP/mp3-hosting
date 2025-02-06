package com.epam.pratsko.repository;

import com.epam.pratsko.entity.ResourceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ResourceRepository extends CrudRepository<ResourceEntity, Long> {
}
