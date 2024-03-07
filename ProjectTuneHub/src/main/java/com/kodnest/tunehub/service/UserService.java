package com.kodnest.tunehub.service;

import com.kodnest.tunehub.entity.User;

public interface UserService {
	//public String addSong(String name);
	public String addUser(User user);
	public boolean emailExists(String email);
	public boolean ValidateUser(String email, String password);
	public String getRole(String email);
	
	public String updateUser(User user);
	public User getUser(String email);
	
	
	

}
