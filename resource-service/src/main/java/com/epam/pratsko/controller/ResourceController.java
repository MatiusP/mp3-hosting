package com.epam.pratsko.controller;

import com.epam.pratsko.service.ResourceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<?> upload(HttpServletRequest request) throws IOException {
        byte[] fileContent = request.getInputStream().readAllBytes();
        long id = resourceService.processResource(fileContent);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getById(@PathVariable("id") String id) {
        byte[] content = resourceService.getById(id);
        return ResponseEntity.ok()
                .header("Content-Type", "audio/mpeg")
                .body(content);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") List<String> ids) {
        List<Long> deletedResourceIds = resourceService.delete(ids);
        return ResponseEntity.ok().body(Map.of("ids", deletedResourceIds));
    }

}
