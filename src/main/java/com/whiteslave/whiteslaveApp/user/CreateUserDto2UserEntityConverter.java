package com.whiteslave.whiteslaveApp.user;

import com.whiteslave.whiteslaveApp.user.domain.dto.CreateUserDto;
import com.whiteslave.whiteslaveApp.user.entity.User;
import org.springframework.stereotype.Component;

@Component
class CreateUserDto2UserEntityConverter {

    public User convert2UserEntity(CreateUserDto createUserDto) {

        return new User()
                .setUsername(createUserDto.getUsername())
                .setLogin(createUserDto.getLogin())
                .setPassword(createUserDto.getPassword())
                .setMatchingPassword(createUserDto.getMatchingPassword())
                .setEmail(createUserDto.getEmail())
                .setAccountNonExpired(createUserDto.isAccountNonExpired())
                .setAccountNonLocked(createUserDto.isAccountNonLocked())
                .setCredentialsNonExpired(createUserDto.isCredentialsNonExpired())
                .setEnabled(createUserDto.isEnabled());
//        return new User();
    }
}
