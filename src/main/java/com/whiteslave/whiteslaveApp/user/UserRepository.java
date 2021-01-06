package com.whiteslave.whiteslaveApp.user;

import com.whiteslave.whiteslaveApp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLogin(String login);
    List<User> findByEmail(String email);
}
