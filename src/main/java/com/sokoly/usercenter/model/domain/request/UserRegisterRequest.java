package com.sokoly.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 8208312057818989350L;

    private String userAccount;
    private String userPassword;
    private String checkPassword;

}
