package com.epam.pratsko.mapper;

import com.epam.pratsko.dto.SongDto;
import com.epam.pratsko.entity.SongEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-25T12:23:50+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class SongMapperImpl implements SongMapper {

    @Override
    public SongEntity toEntity(SongDto songDto) {
        if ( songDto == null ) {
            return null;
        }

        SongEntity songEntity = new SongEntity();

        if ( songDto.getId() != null ) {
            songEntity.setId( Long.parseLong( songDto.getId() ) );
        }
        songEntity.setName( songDto.getName() );
        songEntity.setArtist( songDto.getArtist() );
        songEntity.setAlbum( songDto.getAlbum() );
        songEntity.setDuration( songDto.getDuration() );
        songEntity.setYear( songDto.getYear() );

        return songEntity;
    }

    @Override
    public SongDto toDto(SongEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SongDto songDto = new SongDto();

        if ( entity.getId() != null ) {
            songDto.setId( String.valueOf( entity.getId() ) );
        }
        songDto.setName( entity.getName() );
        songDto.setArtist( entity.getArtist() );
        songDto.setAlbum( entity.getAlbum() );
        songDto.setDuration( entity.getDuration() );
        songDto.setYear( entity.getYear() );

        return songDto;
    }
}
