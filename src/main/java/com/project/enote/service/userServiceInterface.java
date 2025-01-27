package com.project.enote.service;

import com.project.enote.model.user;

public interface userServiceInterface {
    public user saveUser(user u);
    public boolean existEmailCheck(String email);
}
