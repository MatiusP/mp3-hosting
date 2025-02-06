package com.epam.pratsko.service;

import java.util.List;

public interface ResourceService {

    Long processResource(byte[] resource);

    byte[] getById(String id);

    List<Long> delete(List<String> ids);

}
