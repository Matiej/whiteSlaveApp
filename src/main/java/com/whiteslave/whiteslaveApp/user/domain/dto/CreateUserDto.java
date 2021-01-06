package com.whiteslave.whiteslaveApp.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    private String username;
    private String login;
    private String password;
    private String matchingPassword;
    private String email;
//    private String[] authRoles;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
