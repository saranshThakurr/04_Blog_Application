package com.saranshit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saranshit.entity.BlogPosts;

public interface BlogRepo extends JpaRepository<BlogPosts, Integer> {

}
