package com.trading.project.IT.service;

import com.trading.project.entity.User;
import com.trading.project.repository.UserRepository;
import com.trading.project.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class UserServiceTestIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void getUserById() {
        // given
        User savedUser = userRepository.save(
                new User("1", "usr1", "user1@domain.com", "abc123"));

        // when
        User user = userService.getUserById("1");

        // then
        then(user).isNotNull();
        then(user.getEmail()).isEqualTo(savedUser.getEmail());

    }

}
