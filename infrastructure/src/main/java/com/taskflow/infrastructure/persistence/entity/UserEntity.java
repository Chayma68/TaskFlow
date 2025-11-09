package com.taskflow.infrastructure.persistence.entity;

import com.taskflow.core.model.Role;
import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

@Entity @Table(name="users")
@Getter @Setter
public class UserEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true) private String email;
    @Column(nullable=false, unique=true) private String username;
    @Column(name="password_hash", nullable=false) private String passwordHash;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role;
}
