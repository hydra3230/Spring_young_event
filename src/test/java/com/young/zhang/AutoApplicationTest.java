package com.young.zhang;

import com.young.zhang.pojo.User;
import com.young.zhang.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AutoApplicationTest {
    @Autowired
    private UserService userService;

    @Test
    public void test1() {

        User user = new User();
        user.setUsername("Young");
        user.setEmail("young.zhang@mercedes-benz.com");
        user.setPassword("young");
        boolean flag = this.userService.save(user);
        System.out.println(flag);

    }
}
