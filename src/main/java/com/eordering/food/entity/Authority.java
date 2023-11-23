package com.eordering.food.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "roles")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "login", referencedColumnName = "login")
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false, length = 50)
    private Roles role;
    @CreationTimestamp
    @Column(name = "created_time")
    private Timestamp created;
    @CreationTimestamp
    @Column(name = "updated_time")
    private Timestamp updated;

    @Override
    public String getAuthority() {
        return role.name();
    }

    public enum Roles {
        USER, ADMIN, PRODUCER
    }

    @Override
    public String toString() {
        return "Authority{" +
                "role=" + role +
                '}';
    }
}

