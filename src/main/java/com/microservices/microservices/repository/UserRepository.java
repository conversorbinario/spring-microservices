package com.microservices.microservices.repository;

import com.microservices.microservices.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
} 