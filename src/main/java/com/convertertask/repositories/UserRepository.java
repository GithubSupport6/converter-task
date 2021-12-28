package com.convertertask.repositories;

import com.convertertask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {

    public User findByUsername(String username);

}
