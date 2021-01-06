package com.whiteslave.whiteslaveApp.user.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "PERMISSION")
@EqualsAndHashCode
@Accessors(chain = true)
public class Permission {

    @Id
    @Column(name = "PER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PER_NAME")
    private String name;

    @ManyToMany(mappedBy = "permissionSet", targetEntity = Role.class)
    private Set<Role> roleSet = new HashSet<>();

    public Permission(String name) {
        this.name = name;
    }
}
