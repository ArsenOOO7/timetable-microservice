package com.pnu.system.identityaccess.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pnu.system.common.domain.BaseEntityProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "teacher_profile")
public class TeacherProfile implements BaseEntityProvider {

    @JsonIgnore
    @Id
    private String id;
    @Column(name = "user_id", updatable = false, insertable = false)
    private String userId;
    @Column(name = "personal_link")
    private String personalLink;
    @Column(name = "academic_status_id")
    private String academicStatusId;
    @Column(name = "chair_id")
    private String chairId;
    @Version
    @Column(name = "version")
    private Integer version;

    @JsonIgnore
    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
