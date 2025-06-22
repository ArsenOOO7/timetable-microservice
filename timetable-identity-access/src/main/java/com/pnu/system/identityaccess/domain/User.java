package com.pnu.system.identityaccess.domain;

import com.pnu.system.common.constant.UserType;
import com.pnu.system.common.dto.UsernameProvider;
import com.pnu.system.elasticsearch.constant.ElasticsearchIndex;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "app_user")
@Indexed(index = ElasticsearchIndex.USER_INDEX)
public class User extends AbstractUser implements UsernameProvider {

    @Column(name = "password")
    private String password;
    @BatchSize(size = 100)
    @ElementCollection
    @CollectionTable(name = "student_group", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "group_id")
    private List<String> groupIds;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    @KeywordField(searchable = Searchable.YES)
    @Override
    public String getId() {
        return super.getId();
    }

    @FullTextField(searchable = Searchable.YES)
    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @FullTextField(searchable = Searchable.YES)
    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @FullTextField(searchable = Searchable.YES)
    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @KeywordField(searchable = Searchable.YES)
    @Override
    public UserType getType() {
        return super.getType();
    }
}
