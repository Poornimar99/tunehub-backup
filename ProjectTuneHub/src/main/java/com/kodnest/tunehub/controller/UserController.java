package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.serviceimpl.SongServiceimpl;
import com.kodnest.tunehub.serviceimpl.UserServiceimpl;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {

	@Autowired
	UserServiceimpl userserviceimpl;
	
	@Autowired
	SongServiceimpl songserviceimpl;

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user){

		//email taken from registration form
		String email = user.getEmail();	

		//checking if email as entered in registration form is present in DB or not.
		boolean status=userserviceimpl.emailExists(email);

		if(status==false) {//status!=true
			userserviceimpl.addUser(user);
			System.out.println("user added");

		}
		else {
			System.out.println("user is already exists");
		}

		return "login";


	}
	@PostMapping("/validate")
	public String validate(@RequestParam ("email")String email,@RequestParam("password") String password,
			HttpSession session,Model model){

		if(userserviceimpl.ValidateUser(email,password)==true)//fetch email,password in front end entered by user
		{
			String role=userserviceimpl.getRole(email);//fetch the role entered by user

			session.setAttribute("email", email);

			if(role.equals("admin")) 
			{
				return "adminhome";
			}
			else
			{
				User user = userserviceimpl.getUser(email);
				boolean userstatus = user.isIspremium();
				model.addAttribute("ispremium",userstatus);
				List<Song> fetchAllSongs = songserviceimpl.fetchAllSongs();
				model.addAttribute("songs",fetchAllSongs);
				return "customerhome";
			}
		}
		else 
		{
			return "login";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	

	
	}
		
	
	









