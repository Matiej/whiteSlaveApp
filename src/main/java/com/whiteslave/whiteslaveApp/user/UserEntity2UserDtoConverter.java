package com.whiteslave.whiteslaveApp.user;

import com.whiteslave.whiteslaveApp.user.domain.dto.UserDto;
import com.whiteslave.whiteslaveApp.user.entity.User;
import org.springframework.stereotype.Component;


@Component
class UserEntity2UserDtoConverter {

    public UserDto convertToUserDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setLogin(user.getLogin())
                .setPassword(user.getPassword())
                .setMatchingPassword(user.getMatchingPassword())
                .setEmail(user.getEmail())
                .setAccountNonExpired(user.isAccountNonExpired())
                .setAccountNonLocked(user.isAccountNonLocked())
                .setCredentialsNonExpired(user.isCredentialsNonExpired())
                .setEnabled(user.isEnabled());
    }
}
