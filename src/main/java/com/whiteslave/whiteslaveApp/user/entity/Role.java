package com.whiteslave.whiteslaveApp.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@Table(name = "ROLE")
@EqualsAndHashCode
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "ROL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROL_ROLE_NAME")
    private String roleName;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "roleSet", targetEntity = User.class)
    private Set<User> userSet = new HashSet<>();

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "PERMISSION_ROLE",
            joinColumns = {@JoinColumn(name = "ROL_ROLE_ID", referencedColumnName = "ROL_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PER_PERMISSION_ID", referencedColumnName = "PER_ID")})
    private Set<Permission> permissionSet = new HashSet<>();


    public Role(String roleName) {
        this.roleName = roleName;
    }
}

