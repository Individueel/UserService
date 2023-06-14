package com.hoef.mike.userservice.Repositories;

import com.hoef.mike.userservice.Entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}