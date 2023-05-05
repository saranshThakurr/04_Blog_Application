package com.saranshit.service;

import java.util.List;

import com.saranshit.binding.NewPostForm;
import com.saranshit.entity.BlogPosts;

public interface PostService {

	public boolean newPost(NewPostForm form);
	
	public List<BlogPosts> getBlogList();
	
	public BlogPosts editBlog(Integer id);
		
	public boolean updateBlog(NewPostForm form);
	
	public boolean deleteBlog(Integer id);
}
