package com.saranshit.binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class LoginForm {
	private String email;
	private String password;
}
