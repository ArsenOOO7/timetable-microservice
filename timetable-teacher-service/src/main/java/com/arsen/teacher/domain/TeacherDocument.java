package com.arsen.teacher.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "teachers")
@Getter @Setter
public class TeacherDocument {

    @Id
    private long id;

    @Field(type = FieldType.Text)
    private String fullName;

    /*@Field(type = FieldType.Text)
    private String lastName;

    @Field(type = FieldType.Text)
    private String fatherName;;*/

}
