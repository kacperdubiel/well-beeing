package com.wellbeeing.wellbeeing.service.files;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileService {
    void init();
    String save(MultipartFile file);
//    void store(MultipartFile file);

    List<Path> loadAll();

    void deleteAll();

    Resource load(String filename);

//    Resource loadAsResource(String filename);

}
