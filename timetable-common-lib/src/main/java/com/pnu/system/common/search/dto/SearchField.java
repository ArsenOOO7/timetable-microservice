package com.pnu.system.common.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchField {

    private String name;
    private boolean collection;

    public SearchField(String name) {
        this(name, false);
    }
}
