package com.ravionics.service;

import com.ravionics.model.Role;
import com.ravionics.model.Users;
import com.ravionics.repository.RoleRepository;
import com.ravionics.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Users findUsersByEmail(String email)
    {
        return usersRepository.findByEmail(email);
    }

    @Override
    public void saveUsers(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        users.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        usersRepository.save(users);
    }

}