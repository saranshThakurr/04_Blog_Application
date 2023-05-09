package com.saranshit.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saranshit.binding.CommentForm;
import com.saranshit.binding.LoginForm;
import com.saranshit.binding.SignUpForm;
import com.saranshit.entity.BlogPosts;
import com.saranshit.entity.Comments;
import com.saranshit.entity.Users;
import com.saranshit.repo.BlogRepo;
import com.saranshit.repo.CommentsRepo;
import com.saranshit.repo.UserRepo;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	private CommentsRepo comRepo;

	
@Override
	public List<BlogPosts> getFilteredData(String name) {
		// TODO Auto-generated method stub
	if(name!=null) {
	List<BlogPosts> findAll = blogRepo.findAll();
	return findAll.stream().filter(e->e.getTitle().contains(name))
					.collect(Collectors.toList());
		}
		return null;
	}
	
	@Override
	public List<Comments> getComments() {
		// TODO Auto-generated method stub
		Integer blogId = (Integer)session.getAttribute("blogId");
		if(blogId!=null) {
			Optional<BlogPosts> findById = blogRepo.findById(blogId);
			if(findById.isPresent()) {
				BlogPosts blogPosts = findById.get();
				return blogPosts.getComments();
			}
		}
		return null;
	}
	
	@Override
	public boolean saveComments(CommentForm form) {
		// TODO Auto-generated method stub
		Comments entity=new Comments();
		Integer blogId = (Integer)session.getAttribute("blogId");
		if(blogId!=null) {
		Optional<BlogPosts> findById = blogRepo.findById(blogId);
		if(findById.isPresent()) {
			BlogPosts blogPosts = findById.get();
			entity.setBlogPost(blogPosts);
		}
		if(form.getName()!=null && form.getName()!="") {
			entity.setName(form.getName());
		}
		if(form.getComment()!=null && form.getComment()!="") {
			entity.setComment(form.getComment());
		}
		comRepo.save(entity);
		return true;
		}
		return false;
	}
	
	@Override
	public BlogPosts blogDetails(Integer blogId) {
		// TODO Auto-generated method stub
		if(blogId!=null) {
			 Optional<BlogPosts> blogPosts = blogRepo.findById(blogId);
			 if(blogPosts.isPresent()) {
			 return blogPosts.get();
			 }
			}
		return null;
	}
	
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
