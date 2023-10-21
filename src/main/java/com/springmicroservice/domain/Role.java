package com.springmicroservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
@Builder

@Entity
@Table(name="role")
public class Role {
    @Id
    @SequenceGenerator(name = "role_sequence", sequenceName = "ROLE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    @Column(name = "id_role",nullable = false)
    private Long id;

    @Column(name = "role_name",nullable = false)
    private String name;

    @Column(name = "role_description",nullable = false)
    private String description;
}
