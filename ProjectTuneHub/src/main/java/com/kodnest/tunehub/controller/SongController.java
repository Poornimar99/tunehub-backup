package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.serviceimpl.SongServiceimpl;
import com.kodnest.tunehub.serviceimpl.UserServiceimpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class SongController {
	@Autowired
	SongServiceimpl songserviceimpl;
	
	@PostMapping("/addsong")
	public String addsong(@ModelAttribute Song song){

		boolean songstatus=songserviceimpl.songExists(song.getName());//get name from form
		if(songstatus == true) {
			songserviceimpl.addSong(song);
			System.out.println("Song added successfully");
		}
		else {
			System.out.println("song already exist");
		}
		return "adminhome"; 
	}
	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
		List<Song> songList=songserviceimpl.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "displaysongs";
	}

	


}

