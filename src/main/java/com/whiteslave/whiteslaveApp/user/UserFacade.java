package com.whiteslave.whiteslaveApp.user;

import com.whiteslave.whiteslaveApp.user.domain.dto.CreateUserDto;
import com.whiteslave.whiteslaveApp.user.domain.dto.UserDto;

import java.util.List;

public interface UserFacade {

    UserDto createUser(CreateUserDto createUserDto);

    UserDto updaeUser(UserDto userDto);

    List<UserDto> findAll();

    UserDto logIn(String username, String password);

    UserDto logout();
}
