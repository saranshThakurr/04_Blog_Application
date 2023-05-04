package com.saranshit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saranshit.entity.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {

}
