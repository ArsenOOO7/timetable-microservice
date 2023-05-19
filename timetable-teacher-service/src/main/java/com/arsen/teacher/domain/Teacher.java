package com.arsen.teacher.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Builder(toBuilder = true)
public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String meetingLink;

    @Override
    public String toString() {
        return String.join(" ", lastName, firstName, fatherName);
    }
}
