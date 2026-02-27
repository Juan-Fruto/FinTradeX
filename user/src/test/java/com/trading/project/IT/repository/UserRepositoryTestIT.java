package com.trading.project.IT.repository;

import com.trading.project.entity.User;
import com.trading.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
class UserRepositoryTestIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TestEntityManager testEntityManager;


    @Test
    void testGetUsernameById_returnUserEntity() {
        // given
        User savedUser = testEntityManager.persistAndFlush(
                new User("1", "user1", "user1@domain.com", "123"));

        // when
        User user = userRepository.getReferenceById("1");

        // then
        then(savedUser).isNotNull();
        then(user.getEmail()).isEqualTo(savedUser.getEmail());
    }

    @Test
    void testGetAllUsers_returnUserEntityList() {
        // given
        testEntityManager.persistAndFlush(
                new User("1", "user1", "user1@domain.com", "123"));
        testEntityManager.persistAndFlush(
                new User("2", "user2", "user2@domain.com", "231"));
        testEntityManager.persistAndFlush(
                new User("3", "user3", "user3@domain.com", "312"));

        // when
        List<User> users = userRepository.findAll();

        // then
        then(users).isNotNull();
        then(users.size()).isEqualTo(3);
    }

}
