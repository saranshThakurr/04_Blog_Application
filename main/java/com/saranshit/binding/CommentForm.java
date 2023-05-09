package com.saranshit.binding;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
public class CommentForm {

	private String name;
	private String email;
	private String comment;
	
}
