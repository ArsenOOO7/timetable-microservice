package com.pnu.system.file.storage.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;

import java.util.UUID;

@UtilityClass
public class StorageResourcePathUtil {

    public static String buildUuidUri(String filename, String uuid, String folder) {
        String extension = FilenameUtils.getExtension(filename);
        String newFilename = String.join(".", uuid, extension);
        return buildUri(folder, newFilename);
    }

    public static String buildUuidUri(String filename, String folder) {
        return buildUri(filename, UUID.randomUUID().toString(), folder);
    }

    public static String buildUri(String... args) {
        return String.join("/", args);
    }
}
