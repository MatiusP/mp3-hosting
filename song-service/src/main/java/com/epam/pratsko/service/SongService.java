package com.epam.pratsko.service;

import com.epam.pratsko.dto.SongDto;

import java.util.List;

public interface SongService {

    Long save(SongDto songDto);

    SongDto getById(String id);

    List<Long> delete(List<String> ids);

}
