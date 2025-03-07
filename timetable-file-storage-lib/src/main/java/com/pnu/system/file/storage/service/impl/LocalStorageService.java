package com.pnu.system.file.storage.service.impl;

import com.pnu.system.common.constant.TimetableProfiles;
import com.pnu.system.file.storage.exception.StorageResourceException;
import com.pnu.system.file.storage.model.StorageResourceWrapper;
import com.pnu.system.file.storage.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Slf4j
@Profile(TimetableProfiles.LOCAL)
@Service
public class LocalStorageService implements FileStorageService {

    @Value("${file-storage.path:local-storage}")
    private Path storagePath;
    @Value("${file-storage.bucketName}")
    private String bucketName;

    @Override
    public void upload(MultipartFile file, String uri) {
        upload(file, uri, false);
    }

    @Override
    public void upload(MultipartFile file, String uri, boolean publicRead) {
        try {
            Path actualPath = getPath(uri);
            actualPath.toFile().mkdirs();
            Files.copy(file.getInputStream(), actualPath, REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageResourceException("Error while uploading file.", e);
        }
    }

    @Override
    public StorageResourceWrapper download(String uri) {
        Path path = getFilepath(uri);
        try (InputStream fileContent = Files.newInputStream(path)) {
            return StorageResourceWrapper.builder()
                    .content(fileContent)
                    .filename(path.getFileName().toString())
                    .size(Files.size(path))
                    .build();
        } catch (IOException e) {
            throw new StorageResourceException("Error while downloading file.", e);
        }
    }

    @Override
    public void deleteFile(String uri) {
        Path path = getFilepath(uri);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageResourceException("Error while deleting file.", e);
        }
    }

    @Override
    public Path getPath(String uri) {
        return getPath(uri, false);
    }

    @Override
    public Path getPath(String uri, boolean absolute) {
        return absolute ? Path.of(uri) : storagePath.resolve(bucketName).resolve(uri);
    }

    @Override
    public Path getFilepath(String uri) {
        Path filepath = getPath(uri);
        if (!Files.exists(filepath) || !Files.isRegularFile(filepath)) {
            throw new StorageResourceException("Invalid file.");
        }
        return filepath;
    }

    @Override
    public String getUrl(String uri) {
        return getFilepath(uri).toAbsolutePath().toString();
    }
}
