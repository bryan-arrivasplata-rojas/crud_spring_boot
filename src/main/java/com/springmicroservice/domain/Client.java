package com.springmicroservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
@Builder

@Entity
@Table(name="client")
public class Client {
    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "CLIENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name = "id_client")
    private Long id;

    @Column(name = "client_name",nullable = false)
    private String name;

    @Column(name = "client_lastname",nullable = false)
    private String lastname;

    @Column(name = "client_email",nullable = false)
    private String email;

    @Column(name = "client_number",nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
}
