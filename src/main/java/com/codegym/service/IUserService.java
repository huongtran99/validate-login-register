package com.codegym.service;

import com.codegym.model.User;

public interface IUserService extends IGeneralService<User> {
    User checkLogin(String userName, String password);
}
