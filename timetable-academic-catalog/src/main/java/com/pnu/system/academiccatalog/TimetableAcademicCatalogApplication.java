package com.pnu.system.academiccatalog;

import com.pnu.system.common.AbstractTimetableApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TimetableAcademicCatalogApplication extends AbstractTimetableApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimetableAcademicCatalogApplication.class, args);
    }
}