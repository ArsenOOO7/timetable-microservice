package com.pnu.system.identityaccess.domain;

import com.pnu.system.common.constant.UserType;
import com.pnu.system.common.domain.AuditableEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class User extends AuditableEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserType type;
    @BatchSize(size = 100)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
    @BatchSize(size = 100)
    @ElementCollection
    @CollectionTable(name = "student_group", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "group_id")
    private List<String> groupIds;
    @Column(name = "password")
    private String password;

    public List<Role> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }
}
