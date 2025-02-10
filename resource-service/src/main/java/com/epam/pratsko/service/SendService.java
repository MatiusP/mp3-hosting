package com.epam.pratsko.service;

import com.epam.pratsko.dto.MetadataDto;
import com.epam.pratsko.dto.MetadataMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SendService {

    private final RestTemplate restTemplate;
    @Value("${song.service.url:}")
    private String songServiceUrl;

    public void sendCreateRequest(Long id, Metadata metadata) {
        HttpEntity<Map<String, String>> request = preparePostRequest(id, metadata);
        String url = songServiceUrl + "/songs";
        sendPostRequest(url, request);
    }

    private HttpEntity<Map<String, String>> preparePostRequest(Long id, Metadata metadata) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MetadataDto metadataDto = MetadataMapper.toDto(metadata);
        metadataDto.getMetadata().put("id", id.toString());
        return new HttpEntity<>(metadataDto.getMetadata(), headers);
    }

    private void sendPostRequest(String url, HttpEntity<Map<String, String>> request) {
        restTemplate.postForObject(url, request, String.class);
    }

    public void sendDeleteRequest(List<Long> ids) {
        String url = prepareDeleteUrl(songServiceUrl + "/songs", ids);
        HttpEntity<Void> request = prepareDeleteRequest();

        sendDeleteRequest(url, request);
    }

    private String prepareDeleteUrl(String baseUrl, List<Long> ids) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);
        for (Long id : ids) {
            builder.queryParam("id", id);
        }
        return builder.toUriString();
    }

    private HttpEntity<Void> prepareDeleteRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }

    private void sendDeleteRequest(String url, HttpEntity<Void> request) {
        restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
    }
}
