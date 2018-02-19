package com.automovil.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automovil.demo.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserName(String username);
}
