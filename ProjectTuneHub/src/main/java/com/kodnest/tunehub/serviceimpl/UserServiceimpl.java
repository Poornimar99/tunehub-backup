package com.kodnest.tunehub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;
import com.kodnest.tunehub.service.UserService;
@Service
public class UserServiceimpl implements UserService{

	@Autowired
	UserRepository userrepository;

	public String addUser(User user) {

		userrepository.save(user);
		return "successfuly added";
	}

	// to check the duplicate entries  
	public boolean emailExists(String email) {

		if(userrepository.findByEmail(email)!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean ValidateUser(String email, String password) {
		User user = userrepository.findByEmail(email);
		String dbpwd=user.getPassword();
		if(password.equals(dbpwd)) 
		{
			return true;
		}
		else 
		{
			return false;

		}
	}

	public String getRole(String email) {
		User user = userrepository.findByEmail(email);//return the role based on email in the DB
		return user.getRole() ;
	}

	public String addSong(String name) {
		userrepository.save(name);
		return "song addedd";
	}
	public User getUser(String email) {
		return	userrepository.findByEmail(email);
	}
	public void updateUser(User user) {
		userrepository.save(user);
	}



}
