package com.whiteslave.whiteslaveApp.user.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto {

    private Long id;
    private String username;
    private String login;
    private String password;
    private String matchingPassword;
    private String email;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Set<RoleDto> roleDtoSet = new HashSet<>();

    public UserDto(String username, String login, String password, String matchingPassword, String email,
                   boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled,
                   Set<RoleDto> roleDtoSet) {
        this.username = username;
        this.login = login;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.roleDtoSet = roleDtoSet;
    }
}
