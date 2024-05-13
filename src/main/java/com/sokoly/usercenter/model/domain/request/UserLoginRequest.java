package com.sokoly.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 4954609566665931347L;
    private String userAccount;
    private String userPassword;

}
