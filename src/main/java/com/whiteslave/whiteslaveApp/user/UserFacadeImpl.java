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
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class UserFacadeImpl implements UserFacade {

    private final UserRepository userRepository;
    private final CreateUserDto2UserEntityConverter createUserDto2UserEntityConverter;
    private final UserEntity2UserDtoConverter userEntity2UserDtoConverter;
    private final UserDto2UserEntityConverter userDto2UserEntityConverter;

    @Override
    @Transactional
    public UserDto createUser(CreateUserDto createUserDto) {
        User userTosave = createUserDto2UserEntityConverter.convert2UserEntity(createUserDto);

        if(isUserExist(userTosave)) {
            log.error("User exist in data base. Can not add -> " + userTosave.getLogin());
            throw new UserExistException("User exist in data base. Can not add ->  "+ userTosave.getLogin());
        }
        User savedUser;
        try {
             savedUser = userRepository.save(userTosave);
        } catch (Exception e) {
            throw new HibernateException("Can't save user to data base");
        }

        UserDto userDto = userEntity2UserDtoConverter.convert2User(savedUser);

        return userDto;
    }

    private boolean isUserExist(User user) {
        List<User> byEmail = userRepository.findByEmail(user.getEmail());
        List<User> byLogin = userRepository.findByLogin(user.getLogin());
        return byEmail.size() > 0 || byLogin.size() > 0;
    }

    @Override
    public UserDto update(UserDto userDto) {
        if(!userRepository.existsById(userDto.getId())) {
            throw new UserExistException("User id: " + userDto.getId() + " is not exist!");
        }
        UserDto updatedUserDto = null;
        try {
            User user = userDto2UserEntityConverter.convertToUserDto(userDto);
            updatedUserDto = userEntity2UserDtoConverter.convert2User(userRepository.save(user));
        } catch (Exception e) {
            throw new HibernateException("Can't update user");
        }
        return updatedUserDto;
    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<User> userToDelete = userRepository.findById(id);
        userToDelete.ifPresentOrElse(userRepository::delete, () -> {
            throw new UserExistException(String.format("User ID: %s, you try to delete is not exist", id));
        });

    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userEntity2UserDtoConverter::convert2User)
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

    @Override
    public UserDto validateUser() {
        return null;
    }


}
