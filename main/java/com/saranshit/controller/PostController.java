package com.saranshit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.saranshit.binding.NewPostForm;
import com.saranshit.entity.BlogPosts;
import com.saranshit.service.PostService;

@Controller
public class PostController {


	@Autowired
	private HttpSession session;
	
	@Autowired
	private PostService service;
	
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		List<BlogPosts> blogList = service.getBlogList();
		model.addAttribute("lists", blogList);
		return "dashboard";
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/newpost")
	public String newPostPage(Model model) {
		model.addAttribute("objForm", new NewPostForm());
		return "newpost";
	}
	
	@PostMapping("/newpost")
	public String handleNewPost(@ModelAttribute("objForm") NewPostForm form,Model model) {
		if(session.getAttribute("editBlog")!=null && session.getAttribute("editBlog")!="") {
			
		}
		
		boolean newPost = service.newPost(form);
		if(newPost) {
			model.addAttribute("succMsg", "Blog Added Successfully");
		}else {
			model.addAttribute("errMsg", "Something went wrong");
		}
		return "newpost";
	}
	
	@GetMapping("/comment")
	public String commentPage() {
		return "comment";
	}
	
	@GetMapping("/edit/{id}")
	public String updateBlog(@PathVariable("id") Integer id) {
		System.out.println(id);
		BlogPosts editBlog = service.editBlog(id);
		session.setAttribute("editBlog", editBlog);
		session.setAttribute("blogId", id);
		return "redirect:/newpost";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBlog(@PathVariable("id") Integer id) {
		boolean editBlog = service.deleteBlog(id);
		session.setAttribute("editBlog", editBlog);
		session.setAttribute("blogId", id);
		return "newpost";
	}
	

	
}
