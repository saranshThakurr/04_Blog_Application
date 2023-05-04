package com.saranshit.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saranshit.binding.LoginForm;
import com.saranshit.binding.SignUpForm;
import com.saranshit.entity.Users;
import com.saranshit.repo.UserRepo;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean signUp(SignUpForm form) {
		Users findByEmail = userRepo.findByEmail(form.getEmail());
		if(findByEmail !=null) {
			return false;
		}
		Users userEntity=new Users();
		BeanUtils.copyProperties(form, userEntity);
		userRepo.save(userEntity);
		   return true;
	}

	@Override
	public String login(LoginForm form) {
		Users entity = userRepo.findByEmailAndPassword(form.getEmail(), form.getPassword());
		if(entity==null) {
			return "Invalid credentials";
		}
		session.setAttribute("userId", entity.getUserId());
		return "success";
	}

}
