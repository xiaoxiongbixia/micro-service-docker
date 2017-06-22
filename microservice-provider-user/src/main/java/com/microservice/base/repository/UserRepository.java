package com.microservice.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.base.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
