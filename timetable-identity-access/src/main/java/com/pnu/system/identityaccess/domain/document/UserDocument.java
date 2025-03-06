package com.pnu.system.identityaccess.domain.document;

import com.pnu.system.common.constant.UserType;
import com.pnu.system.elasticsearch.constant.ElasticsearchIndex;
import com.pnu.system.elasticsearch.domain.BaseDocument;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

@Getter
@Setter
@Document(indexName = ElasticsearchIndex.USER_INDEX)
public class UserDocument extends BaseDocument {

    @MultiField(mainField = @Field(type = FieldType.Text), otherFields = {@InnerField(suffix = "keyword", type = FieldType.Keyword)})
    private String firstName;
    @MultiField(mainField = @Field(type = FieldType.Text), otherFields = {@InnerField(suffix = "keyword", type = FieldType.Keyword)})
    private String lastName;
    @MultiField(mainField = @Field(type = FieldType.Text), otherFields = {@InnerField(suffix = "keyword", type = FieldType.Keyword)})
    private String email;
    @Field(type = FieldType.Keyword)
    private UserType type;

}
