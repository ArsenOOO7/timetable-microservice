package com.pnu.system.identityaccess.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class UserSearch extends AbstractUser {

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private TeacherProfile teacherProfile;

}
