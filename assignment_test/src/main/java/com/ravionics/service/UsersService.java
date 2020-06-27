package com.ravionics.service;

import com.ravionics.model.Users;

public interface UsersService {

   public Users findUsersByEmail(String email);

    public void saveUsers(Users users);

}