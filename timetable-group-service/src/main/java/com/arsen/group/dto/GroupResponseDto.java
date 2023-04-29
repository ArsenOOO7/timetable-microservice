package com.arsen.group.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class GroupResponseDto {

    private long id;
    private String cypher;

    private short academicYear;
    private short number;

    private boolean master;
    private boolean college;
    private boolean collective;

    private Set<Long> groupIds = new HashSet<>();


    public GroupResponseDto(long id, String cypher, short academicYear, short number, boolean master, boolean college, boolean collective) {
        this.id = id;
        this.cypher = cypher;
        this.academicYear = academicYear;
        this.number = number;
        this.master = master;
        this.college = college;
        this.collective = collective;
    }


    @Override
    public String toString() {
        return cypher +
                (master ? 'м' : "") +
                (college ? 'к' : "") +
                '-' +
                academicYear +
                (collective ? '.' : "") +
                number;
    }
}
