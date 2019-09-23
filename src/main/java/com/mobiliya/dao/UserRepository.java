package com.mobiliya.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobiliya.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Find User by Name.
	Optional<User> findByUsername(String username);

}