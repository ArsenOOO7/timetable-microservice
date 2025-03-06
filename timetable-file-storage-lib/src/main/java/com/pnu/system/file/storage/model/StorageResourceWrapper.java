package com.pnu.system.file.storage.model;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

@Builder
@Getter
public class StorageResourceWrapper {

    private InputStream content;
    private String filename;
    private long size;

}
