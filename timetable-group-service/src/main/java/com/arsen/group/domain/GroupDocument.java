package com.arsen.group.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "groups")
@Getter @Setter
public class GroupDocument {

    @Id
    private long id;

    @Field(type = FieldType.Text)
    private String groupName;

}
