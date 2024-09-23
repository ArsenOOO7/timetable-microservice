package com.pnu.system.academiccatalog.api.dto;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class ChairCreateDto {
    private String name;
    private String shortName;
    private String departmentId;
    private List<String> specialtiesIds;
}
