package com.pnu.system.elasticsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseDocument {

    @Id
    @Field(type = FieldType.Keyword)
    private UUID id;

    @Deprecated
    public BaseDocument(String id) {
        setStringId(id);
    }

    public boolean isNew() {
        return Objects.isNull(id);
    }

    @Deprecated
    public void setStringId(String id) {
        this.id = UUID.fromString(id);
    }

    @Deprecated
    public String getStringId() {
        return isNew() ? null : id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDocument that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
