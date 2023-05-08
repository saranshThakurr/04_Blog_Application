package com.saranshit.service;

import com.saranshit.binding.LoginForm;
import com.saranshit.binding.SignUpForm;
import com.saranshit.entity.BlogPosts;

public interface UserService {

	public boolean signUp(SignUpForm form);
	public String login(LoginForm form);
	public BlogPosts blogDetails(Integer blogId);
	
}
