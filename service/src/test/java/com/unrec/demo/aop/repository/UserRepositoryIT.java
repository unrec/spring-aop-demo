package com.unrec.demo.aop.repository;

import com.unrec.demo.aop.aspect.service.LogService;
import com.unrec.demo.aop.domain.UserEntity;
import com.unrec.demo.aop.exception.NotFoundException;
import com.unrec.demo.aop.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @BeforeEach
    void setUp() {
        loadUsers();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void initDb() {
        List<UserEntity> userEntityList = userRepository.findAll();
        assertThat(userEntityList.size(), is(3));
    }

    @Test
    void getByName_Success() {
        //when
        UserEntity user = userService.getUserByName("Jim");

        //then
        assertThat(user.getFirstName(), is("Jim"));
    }

    @Test
    void getByName_Fail() {
        Assertions.assertThrows(NotFoundException.class, () -> userService.getUserByName("User"));
    }

    @Test
    void testAudit() {
        //when
        UserEntity user = userService.getUserByName("John");

        //then
        assertThat(logService.getLogs(), hasSize(1));
    }

    private void loadUsers() {
        UserEntity user1 = UserEntity.builder().firstName("John").lastName("Doe").email("john@xyz.com").rating(5).build();
        UserEntity user2 = UserEntity.builder().firstName("Jane").lastName("Doe").email("jane@xyz.com").rating(3).build();
        UserEntity user3 = UserEntity.builder().firstName("Jim").lastName("Doe").email("jim@xyz.com").rating(1).build();
        userRepository.saveAll(Arrays.asList(user1, user2, user3));
    }
}