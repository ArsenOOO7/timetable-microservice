package com.pnu.system.file.storage.utils;

import com.pnu.system.file.storage.model.StorageResourceWrapper;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;

@UtilityClass
public class StorageResourceUtil {

    private static final FileNameMap FILE_NAME_MAP = URLConnection.getFileNameMap();

    public static ResponseEntity<InputStreamResource> asResponse(StorageResourceWrapper resource) {
        return asResponse(resource.getContent(), resource.getFilename(), resource.getSize());
    }

    public static ResponseEntity<InputStreamResource> asResponse(InputStream content, String filename, long size) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.join("", "attachment;filename=", filename))
                .contentType(MediaType.parseMediaType(FILE_NAME_MAP.getContentTypeFor(filename)))
                .contentLength(size)
                .body(new InputStreamResource(content));
    }

}
