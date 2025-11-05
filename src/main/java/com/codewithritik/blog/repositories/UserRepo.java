package com.codewithritik.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithritik.blog.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{
    
}
