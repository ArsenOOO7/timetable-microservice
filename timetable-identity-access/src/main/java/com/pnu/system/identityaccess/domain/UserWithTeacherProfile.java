package com.pnu.system.identityaccess.domain;

import com.pnu.system.common.constant.UserType;
import com.pnu.system.common.domain.AuditableEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "app_user")
@SecondaryTable(name = "teacher_profile",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class UserWithTeacherProfile extends AuditableEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserType type;

    @Column(name = "personal_link", table = "teacher_profile")
    private String personalLink;
    @Column(name = "academic_status_id", table = "teacher_profile")
    private String academicStatusId;
    @Column(name = "chair_id", table = "teacher_profile")
    private String chairId;

    @BatchSize(size = 100)
    @ElementCollection
    @CollectionTable(name = "student_group", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "group_id")
    private List<String> groupIds;

}
