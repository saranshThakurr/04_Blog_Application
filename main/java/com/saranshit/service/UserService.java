package com.saranshit.service;

import java.util.List;

import com.saranshit.binding.CommentForm;
import com.saranshit.binding.LoginForm;
import com.saranshit.binding.SignUpForm;
import com.saranshit.entity.BlogPosts;
import com.saranshit.entity.Comments;

public interface UserService {

	public boolean signUp(SignUpForm form);
	public String login(LoginForm form);
	public BlogPosts blogDetails(Integer blogId);
	public boolean saveComments(CommentForm form);
	public List<Comments> getComments();
	public List<BlogPosts> getFilteredData(String name);
}
