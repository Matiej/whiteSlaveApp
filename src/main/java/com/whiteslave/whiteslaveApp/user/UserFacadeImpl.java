package com.whiteslave.whiteslaveApp.user;

import com.whiteslave.whiteslaveApp.user.domain.dto.CreateUserDto;
import com.whiteslave.whiteslaveApp.user.domain.dto.UserDto;
import com.whiteslave.whiteslaveApp.user.entity.User;
import com.whiteslave.whiteslaveApp.user.exception.UserExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class UserFacadeImpl implements UserFacade {

    private final UserRepository userRepository;
    private final CreateUserDto2UserEntityConverter createUserDto2UserEntityConverter;
    private final UserEntity2UserDtoConverter userEntity2UserDtoConverter;

    @Override
    @Transactional
    public UserDto createUser(CreateUserDto createUserDto) {
        User userTosave = createUserDto2UserEntityConverter.convert2UserEntity(createUserDto);

        if(isUserExist(userTosave)) {
            log.error("User exist in data base");
            throw new UserExistException("User exist in data base");
        }
        User savedUser;
        try {
             savedUser = userRepository.save(userTosave);
        } catch (Exception e) {
            throw new HibernateException("Can't save user to data base");
        }

        UserDto userDto = userEntity2UserDtoConverter.convertToUserDto(savedUser);

        return userDto;
    }

    private boolean isUserExist(User user) {
        List<User> byEmail = userRepository.findByEmail(user.getEmail());
        List<User> byLogin = userRepository.findByLogin(user.getLogin());
        return byEmail.size() > 0 || byLogin.size() > 0;
    }

    @Override
    public UserDto updaeUser(UserDto userDto) {
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userEntity2UserDtoConverter::convertToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto logIn(String username, String password) {
        return null;
    }

    @Override
    public UserDto logout() {
        return null;
    }
}
