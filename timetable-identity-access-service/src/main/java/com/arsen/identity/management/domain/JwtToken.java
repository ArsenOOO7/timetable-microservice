package com.arsen.identity.management.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "access_tokens")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {

    @Id
    private long id;

    @Column(nullable = false)
    private String token;

    public JwtToken(long id) {
        this.id = id;
    }
}
