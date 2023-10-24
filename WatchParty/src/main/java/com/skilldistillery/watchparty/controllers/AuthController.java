package com.skilldistillery.watchparty.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.watchparty.services.AuthService;

@RestController
@CrossOrigin({"*", "http://localhost/"})
public class AuthController {
	
  @Autowired
  private AuthService authService;
}
