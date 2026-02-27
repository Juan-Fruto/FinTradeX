package com.trading.project.IT.service;

import com.trading.project.entity.User;
import com.trading.project.exception.ResourceNotFoundException;
import com.trading.project.repository.UserRepository;
import com.trading.project.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class UserServiceTestIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void test_GivenValidUser_WhenSave_Then_ReturnSavedUser() {
        // given
        User user = new User("1", "usr1", "user1@domain.com", "abc123");

        // when
        User savedUser = userService.saveUser(user);

        // then
        then(savedUser).isNotNull();
        then(savedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void test_GivenInvalidUserWithoutId_WhenSave_Then_ReturnSavedUser() {
        // given
        User user = new User();
        user.setUsername("usr1");
        user.setEmail("user1@domain.com");
        user.setPassword("abc123");

        // when
        Throwable thrown = catchThrowable(() -> userService.saveUser(user));

        // then
        assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Identifier of entity 'com.trading.project.entity.User' must be manually assigned before calling 'persist()");
    }

    @Test
    void test_GivenExistingUser_WhenGetById_Then_ReturnUser() {
        // given
        User savedUser = userRepository.save(
                new User("1", "usr1", "user1@domain.com", "abc123"));

        // when
        User user = userService.getUserById("1");

        // then
        then(user).isNotNull();
        then(user.getEmail()).isEqualTo(savedUser.getEmail());

    }

    @Test
    void test_GivenNonExistingUser_WhenGetById_Then_Throw() {
        // given
        userRepository.save(
                new User("1", "usr1", "user1@domain.com", "abc123"));

        // when
        Throwable thrown = catchThrowable(() -> userService.getUserById("2"));

        // then
        assertThat(thrown)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("User not found");

    }

}
