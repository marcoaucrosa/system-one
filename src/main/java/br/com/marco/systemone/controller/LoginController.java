package br.com.marco.systemone.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@PostMapping("/")
	public String login() {
		return "login";
	}
}
