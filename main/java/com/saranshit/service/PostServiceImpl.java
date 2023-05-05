package com.saranshit.service;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saranshit.binding.NewPostForm;
import com.saranshit.entity.BlogPosts;
import com.saranshit.entity.Users;
import com.saranshit.repo.BlogRepo;
import com.saranshit.repo.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public List<BlogPosts> getBlogList() {
		// TODO Auto-generated method stub
		Integer userId = (Integer)session.getAttribute("userId");
		Optional<Users> findById = userRepo.findById(userId);
		if(findById.isPresent()) {
			Users users = findById.get();
			 List<BlogPosts> posts = users.getPosts();
			 return posts;
		}
		return null;
	}
	
	
	@Override
	public BlogPosts editBlog(Integer id) {
		// TODO Auto-generated method stub
		Optional<BlogPosts> findById = blogRepo.findById(id);
		if(findById.isPresent()) {
			BlogPosts blogPosts = findById.get();
			return blogPosts;
		}
		return null;
	}
	
	
	@Override
	public boolean updateBlog(NewPostForm form) {
		// TODO Auto-generated method stub
		
		  if(session.getAttribute("blogId")!=null) { 
			  Integer blogId =(Integer)session.getAttribute("blogId");
			  BlogPosts blogPosts =blogRepo.findById(blogId).get();
			  BeanUtils.copyProperties(form, blogPosts);
		  session.removeAttribute("blogId"); 
		  blogRepo.save(blogPosts); return true;
		  
		  }
		 
		return false;
	}
	
	@Override
	public boolean deleteBlog(Integer id) {
		// TODO Auto-generated method stub
		
		  if(id!=null) {
			  blogRepo.deleteById(id); 
			  return true; 
			  }
		 
		return false;
	}
	
	@Override
	public boolean newPost(NewPostForm form) {
		// TODO Auto-generated method stub
		BlogPosts entity=new BlogPosts();
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId !=null) {
		 Users users = userRepo.findById(userId).get();
		entity.setUser(users);
		if(form.getTitle()!=null && form.getTitle()!="") {
			entity.setTitle(form.getTitle());
		}
		if(form.getDescription()!=null && form.getDescription()!="") {
			entity.setDescription(form.getDescription());
		}
		if(form.getContent()!=null && form.getContent()!="") {
			entity.setContent(form.getContent());
		}
		
		blogRepo.save(entity);
		return true;
		}
		return false;
	}
}
