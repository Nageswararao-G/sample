package com.nagesh.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagesh.blog.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
