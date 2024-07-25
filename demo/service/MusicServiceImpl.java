package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Music;
import com.example.demo.repository.MusicCrudRepository;

@Service
public class MusicServiceImpl implements MusicService {
	
	@Autowired
	MusicCrudRepository repository;

	@Override
	public Iterable<Music> findAll() {
		return repository.findAll();
	}
	
	@Override
	public void insertMusic(Music music) {
		repository.save(music);
	}
	
	@Override
	public Optional<Music> findById(Integer sondID){
		return repository.findById(sondID);
	}
	
	@Override
	public void updateMusic(Music music) {
		repository.save(music);
	}
	
	@Override
	public void deleteMusic(Integer songID) {
		repository.deleteById(songID);
	}

}