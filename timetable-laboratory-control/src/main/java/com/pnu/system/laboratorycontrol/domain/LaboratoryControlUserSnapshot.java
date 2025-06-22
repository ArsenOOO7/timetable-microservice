package com.pnu.system.laboratorycontrol.domain;

import com.pnu.system.common.constant.UserType;
import com.pnu.system.common.domain.BaseSnapshotEntity;
import com.pnu.system.common.dto.UsernameProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_snapshot")
public class LaboratoryControlUserSnapshot extends BaseSnapshotEntity implements UsernameProvider {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "personal_link")
    private String personalLink;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserType type;
    @Column(name = "deleted")
    private boolean deleted;

}
