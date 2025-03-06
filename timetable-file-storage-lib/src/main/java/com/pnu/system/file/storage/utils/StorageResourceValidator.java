package com.pnu.system.file.storage.utils;

import com.pnu.system.common.exception.InvalidParameterException;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.util.Set;

@UtilityClass
public class StorageResourceValidator {

    private static final Set<String> VALID_IMAGES = Set.of(ImageIO.getReaderFormatNames());

    public static void validateImageFile(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!VALID_IMAGES.contains(extension)) {
            throw new InvalidParameterException("Invalid image mime type.");
        }
    }
}
