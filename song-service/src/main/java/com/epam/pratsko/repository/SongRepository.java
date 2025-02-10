package com.epam.pratsko.repository;

import com.epam.pratsko.entity.SongEntity;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<SongEntity, Long> {
}
