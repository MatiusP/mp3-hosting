package com.epam.pratsko.controller;

import com.epam.pratsko.dto.SongDto;
import com.epam.pratsko.service.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody SongDto songDto) {
        long id = songService.save(songDto);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(songService.getById(id));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") List<String> ids) {
        List<Long> deletedResourceIds = songService.delete(ids);
        return ResponseEntity.ok().body(Map.of("ids", deletedResourceIds));
    }

}
