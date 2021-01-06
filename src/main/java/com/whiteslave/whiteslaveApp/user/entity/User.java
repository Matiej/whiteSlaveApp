package com.whiteslave.whiteslaveApp.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    @Id
    @Column(name = "USR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USR_USERNAME")
    private String username;
    @Column(name = "USR_LOGIN")
    private String login;
    @JsonIgnore
    @Column(name = "USR_PASSWORD")
    private String password;
    @JsonIgnore
    @Column(name = "USR_MATCHING_PASSWORD")
    private String matchingPassword;
    @Column(name = "USR_EMAIL")
    private String email;

    @Column(name = "USR_ACCOUNT_NON_EXPIRED")
    private boolean accountNonExpired;
    @Column(name = "USR_ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked;
    @Column(name = "USR_CREDENTIAL_NON_EXPIRED")
    private boolean credentialsNonExpired;
    @Column(name = "USR_ENABLED")
    private boolean enabled;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_USER",
            joinColumns = {@JoinColumn(name = "USR_USER_ID", referencedColumnName = "USR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROL_ROLE_ID", referencedColumnName = "ROL_ID")})
    private Set<Role> roleSet = new HashSet<>();

    public User(String username, String login, String password, String matchingPassword, String email,
                boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.username = username;
        this.login = login;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public User(User user) {
        this.username = user.getUsername();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
        this.accountNonExpired = user.isAccountNonExpired();
        this.credentialsNonExpired = user.isCredentialsNonExpired();
        this.accountNonLocked = user.isAccountNonLocked();
        this.roleSet = user.getRoleSet();
    }
}
