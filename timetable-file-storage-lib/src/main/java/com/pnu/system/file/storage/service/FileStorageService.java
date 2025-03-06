package com.pnu.system.file.storage.service;

import com.pnu.system.file.storage.model.StorageResourceWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {

    void upload(MultipartFile file, String uri);

    void upload(MultipartFile file, String uri, boolean publicRead);

    StorageResourceWrapper download(String uri);

    void deleteFile(String uri);

    Path getPath(String uri);

    Path getPath(String uri, boolean absolute);

    Path getFilepath(String uri);

    String getUrl(String uri);
}
