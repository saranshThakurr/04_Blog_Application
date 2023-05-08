package com.saranshit.controller;


import java.security.Provider.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.saranshit.binding.LoginForm;
import com.saranshit.binding.SignUpForm;
import com.saranshit.entity.BlogPosts;
import com.saranshit.service.PostService;
import com.saranshit.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	public PostService postService;
	
	
	@GetMapping("/")
	public String getIndexPage(Model model) {
		List<BlogPosts> allBlogs = postService.getAllBlogs();
		model.addAttribute("allBlogs", allBlogs);
		return "index";
	}
	@GetMapping("/post/{blogId}")
	public String getPostsDetails(@PathVariable("blogId") Integer blogId ,Model model) {
		BlogPosts blogDetails = service.blogDetails(blogId);
		model.addAttribute("blogDetails", blogDetails);
		return "post";
	}
	
	@GetMapping("/signup")
	public String signUp(Model model) {
		SignUpForm form=new SignUpForm();
		model.addAttribute("objForm", form);
		return"signup";
	}
	
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("objForm") SignUpForm form ,Model model) {
		boolean signUp = service.signUp(form);
		if(signUp) {
			model.addAttribute("succMsg", "Sign Up Successful");
		}else {
			model.addAttribute("errMsg", "Something went wrong");
		}
		return"signup";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		LoginForm form=new LoginForm();
		model.addAttribute("objForm", form);
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPage(@ModelAttribute("objForm") LoginForm form,Model model) {
		String login = service.login(form);
		if(login.contains("success")) {
			return "redirect:/dashboard";
		}
		model.addAttribute("errMsg", "Either email or password is incorrect");
		return "login";
	}
	
	
	
}
