package com.sokoly.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sokoly.usercenter.model.domain.User;
import com.sokoly.usercenter.model.domain.request.UserLoginRequest;
import com.sokoly.usercenter.model.domain.request.UserRegisterRequest;
import com.sokoly.usercenter.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sokoly.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.sokoly.usercenter.constant.UserConstant.USER_LOGIN_STATE;


/**
 * 用户接口
 */

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Long register(@RequestBody UserRegisterRequest userRegisterRequest) {
        if(userRegisterRequest==null){
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if(userLoginRequest==null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        return userService.userLogin(userAccount, userPassword,request);
    }

    @GetMapping("/search")
    public List<User> list(String username,HttpServletRequest request) {
        if(!isAdmin(request)){
            return new ArrayList<User>();
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username", username);
        }
        List<User> list = userService.list(queryWrapper);
        return list.stream()
                .map(user -> userService.getSafetyUser(user))
                .collect(Collectors.toList());

    }

    @PostMapping("/delete")
    public Boolean delete(@RequestBody long id, HttpServletRequest request) {
        if(!isAdmin(request)) return false;

        if(id<=0) return false;
        return userService.removeById(id);
    }

    private boolean isAdmin(HttpServletRequest request) {
        Object UserObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) UserObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
