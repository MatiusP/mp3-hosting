package com.epam.pratsko.service;

import com.epam.pratsko.dto.SongDto;
import com.epam.pratsko.entity.SongEntity;
import com.epam.pratsko.exception.SongExistsException;
import com.epam.pratsko.exception.SongNotFoundException;
import com.epam.pratsko.mapper.SongMapper;
import com.epam.pratsko.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.pratsko.exception.ExceptionConstants.SONG_EXISTS_ERROR;
import static com.epam.pratsko.exception.ExceptionConstants.SONG_NOT_FOUND_ERROR;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository repository;
    private final SongMapper mapper;

    @Override
    public Long save(SongDto songDto) {
        String id = songDto.getId();
        if (repository.findById(SongValidator.validateAndCastSongId(id)).isPresent()) {
            throw new SongExistsException(String.format(SONG_EXISTS_ERROR, id));
        }
        return repository.save(mapper.toEntity(songDto)).getId();
    }

    @Override
    public SongDto getById(String id) {
        long idAsLong = SongValidator.validateAndCastSongId(id);
        return repository.findById(idAsLong)
                .map(mapper::toDto)
                .orElseThrow(() -> new SongNotFoundException(String.format(SONG_NOT_FOUND_ERROR, idAsLong)));
    }

    @Override
    public List<Long> delete(List<String> ids) {
        List<Long> idsForDeleting = SongValidator.validateListSongsIds(ids);
        List<SongEntity> songsToDelete = (List<SongEntity>) repository.findAllById(idsForDeleting);
        List<Long> deletedIds = songsToDelete.stream()
                .map(SongEntity::getId)
                .toList();
        repository.deleteAll(songsToDelete);
        return deletedIds;
    }
}
