package com.saranshit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saranshit.entity.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {

	public Users findByEmail(String email);
	public Users findByEmailAndPassword(String email,String password);

}
