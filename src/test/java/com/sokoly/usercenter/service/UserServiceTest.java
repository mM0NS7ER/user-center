package com.sokoly.usercenter.service;

import com.sokoly.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setId(0L);
        user.setUsername("sokoly");
        user.setUserAccount("14334494");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setUserPassword("12345678");
        user.setPhone("123");
        user.setEmail("5654");
        user.setUserStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsDelete(0);
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
        //userService.userRegister("14334494","123456789","123456789");
    }

    @Test
    public void testRegister(){
        long l = userService.userRegister("14334", "123456789", "123456789");
        Assertions.assertTrue(l > 0);
    }



}