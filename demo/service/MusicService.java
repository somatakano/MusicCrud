package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Music;

public interface MusicService {
	
	Iterable<Music> findAll();

	void insertMusic(Music music);
	
	void updateMusic(Music music);
	
	Optional<Music> findById(Integer songID);
	
	void deleteMusic(Integer songID);
}
