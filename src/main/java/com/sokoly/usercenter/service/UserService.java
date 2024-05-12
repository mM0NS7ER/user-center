package com.sokoly.usercenter.service;

import com.sokoly.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author YQing
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-05-12 11:57:42
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * //@param planetCode    星球编号
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword /*TODO String planetCode*/);

}
