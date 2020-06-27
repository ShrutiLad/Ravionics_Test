package com.ravionics.repository;

import com.ravionics.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("usersRepository")
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
}
