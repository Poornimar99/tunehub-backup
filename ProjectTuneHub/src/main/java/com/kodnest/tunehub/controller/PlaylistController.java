package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimpl.PlaylistServiceimpl;
import com.kodnest.tunehub.serviceimpl.SongServiceimpl;

@Controller
public class PlaylistController {
	@Autowired
	SongServiceimpl songserviceimpl;//used to fetch the songs 

	@Autowired
	PlaylistServiceimpl playlistserviceimpl;//used to create playlist example contact in the phone to what'sapp contact

	@GetMapping("/createplaylists")
	public String createplaylists(Model model) {
		List<Song> songList=songserviceimpl.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createplaylists";
	}

	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist) {//@Modelattribute is take data from form to backend
		
		//updating the playlist table
		playlistserviceimpl.addplaylist(playlist);//songs from form is addedd to DB

		//updating the song table or connecting the song and playlist tables
		List<Song> songsList = playlist.getSongs();
		for(Song s:songsList) {
			s.getPlaylists().add(playlist);
			songserviceimpl.updateSong(s);//update the song-playlist table
		}
		return "adminhome";

	}
	
	@GetMapping("/viewplaylists")//this is the respective action name in the form
	public String viewplaylists(Model model) { //string-return that songs/html file  
	List<Playlist> fetchallplaylist = playlistserviceimpl.fetchAllPlaylist();
	model.addAttribute("playlistsong",fetchallplaylist);
		return "displayplaylist";



	}
}

