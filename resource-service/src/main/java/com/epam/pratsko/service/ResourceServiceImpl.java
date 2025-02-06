package com.epam.pratsko.service;

import com.epam.pratsko.entity.ResourceEntity;
import com.epam.pratsko.exception.ProcessResourceException;
import com.epam.pratsko.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.metadata.Metadata;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository repository;
    private final ResourceParser parser;

    @Override
    public Long processResource(byte[] resource) {
        Metadata metadata = extractMetadataFrom(resource);
        sendMetadataToSongService(metadata);
        return uploadResource(resource);
    }

    private Metadata extractMetadataFrom(byte[] resource) {
        return parser.extractMetadata(resource);
    }

    private void sendMetadataToSongService(Metadata metadata) {
        //FIXME: implement sending metadata to song service

    }

    private Long uploadResource(byte[] resource) {
        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setContent(resource);
        return repository.save(resourceEntity).getId();
    }

    @Override
    public byte[] getById(String id) {
        long receivedId = Validator.validateAndCastResourceId(id);

        return repository.findById(receivedId)
                .map(ResourceEntity::getContent)
                .orElseThrow(() -> new ProcessResourceException("The provided ID is invalid."));
    }

    @Override
    public List<Long> delete(List<String> ids) {
        List<Long> idsForDeleting = Validator.validateListResourceIds(ids);
        List<ResourceEntity> resourcesToDelete = (List<ResourceEntity>) repository.findAllById(idsForDeleting);
        List<Long> deletedIds = resourcesToDelete.stream()
                .map(ResourceEntity::getId)
                .toList();
        repository.deleteAll(resourcesToDelete);
        return deletedIds;
    }

}
