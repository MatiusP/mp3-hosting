package com.epam.pratsko.mapper;

import com.epam.pratsko.dto.SongDto;
import com.epam.pratsko.entity.SongEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mapping(target = "id", source = "id", resultType = Long.class)
    SongEntity toEntity(SongDto songDto);

    @Mapping(target = "id", source = "id", resultType = String.class)
    SongDto toDto(SongEntity entity);

}
