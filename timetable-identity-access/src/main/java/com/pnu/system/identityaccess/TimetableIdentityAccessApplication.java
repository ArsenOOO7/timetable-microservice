package com.pnu.system.identityaccess;

import com.pnu.system.common.AbstractTimetableApplication;
import com.pnu.system.elasticsearch.annotation.EnableTimetableElasticsearch;
import com.pnu.system.file.storage.annotation.EnableTimetableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableTimetableFileStorage
@EnableTimetableElasticsearch
@SpringBootApplication
public class TimetableIdentityAccessApplication extends AbstractTimetableApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimetableIdentityAccessApplication.class, args);
    }

}