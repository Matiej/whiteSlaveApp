package com.whiteslave.whiteslaveApp.user.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class RoleDto {

    private Long id;
    private String roleName;
    private Set<UserDto> userDtoSet = new HashSet<>();

}
