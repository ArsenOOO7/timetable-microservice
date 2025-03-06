package com.pnu.system.file.storage.service.impl;

import com.pnu.system.common.constant.TimetableProfiles;
import com.pnu.system.file.storage.model.StorageResourceWrapper;
import com.pnu.system.file.storage.service.FileStorageService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Profile(TimetableProfiles.PRODUCTION)
@Service
public class S3StorageService implements FileStorageService {

    @Override
    public void upload(MultipartFile file, String uri) {
        upload(file, uri, false);
    }

    @Override
    public void upload(MultipartFile file, String uri, boolean publicRead) {
        throw new UnsupportedOperationException("Uploading files to S3 is not implemented for now.");
    }

    @Override
    public StorageResourceWrapper download(String uri) {
        throw new UnsupportedOperationException("Downloading files from S3 is not implemented for now.");
    }

    @Override
    public void deleteFile(String uri) {
        throw new UnsupportedOperationException("Deleting files from S3 is not implemented for now.");
    }

    @Override
    public Path getPath(String uri) {
        return getPath(uri, false);
    }

    @Override
    public Path getPath(String uri, boolean absolute) {
        throw new UnsupportedOperationException("Building full path on S3 is not implemented yet.");
    }

    @Override
    public Path getFilepath(String uri) {
        throw new UnsupportedOperationException("Building full file path on S3 is not implemented for now.");
    }

    @Override
    public String getUrl(String uri) {
        throw new UnsupportedOperationException("Building full url on S3 is not implemented for now.");
    }
}
