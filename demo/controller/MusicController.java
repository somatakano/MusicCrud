package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Music;
import com.example.demo.form.MusicForm;
import com.example.demo.service.MusicService;

@Controller
public class MusicController {
	
	@Autowired
	MusicService service;
	
	@GetMapping("index")
	public String indexView() {
		return "top";
	}
	
	@GetMapping("move")
	public String moveView() {
		return "top";
	}
	
	@GetMapping("toList")
	public String toListView(Model model) {
		Iterable<Music> list = service.findAll();
		model.addAttribute("list", list);
		return "list";
	}
	
	//一覧表示
	@PostMapping(value="menu", params="select")
	public String listView(Model model) {
		Iterable<Music> list = service.findAll();
		model.addAttribute("list", list);
		return "list";
	}
	
	
	//追加
	@PostMapping(value="menu", params="insert")
	public String insertView() {
		return "input";
	}
	
	@PostMapping("insertM")
	public String insertCompView(MusicForm f) {
		Music music = new Music(
				f.getSong_id(),
				f.getSong_name(),
				f.getSinger()
				);
		service.insertMusic(music);
		return "input-comp";
	}
	
	
	//更新
	@PostMapping(value="menu", params="update")
	public String updateView(Model model) {
		Iterable<Music> list = service.findAll();
		model.addAttribute("list", list);
		return "update";
	}
	@PostMapping("updateM")
	public String updateCompView(@RequestParam("songID") Integer songID, Model model) {
		Optional<Music> getID = service.findById(songID);
		if (getID.isPresent()) {
			Music music = getID.get();
            MusicForm musicForm = new MusicForm();
            musicForm.setSong_id(music.getSong_id());
            musicForm.setSong_name(music.getSong_name());
            musicForm.setSinger(music.getSinger());
            model.addAttribute("musicForm", musicForm);
            return "update-input";
        } else {
            return "error";
        }
	}
	
	@PostMapping("updateComp")
    public String updateCompView(MusicForm f) {
        Music music = new Music(
                f.getSong_id(),
                f.getSong_name(),
                f.getSinger()
                );
        service.updateMusic(music);
        return "update-comp";
    }
	
	
	//削除
	@PostMapping(value="menu", params="delete")
	public String deleteView(Model model) {
		Iterable<Music> list = service.findAll();
		model.addAttribute("list", list);
		return "delete";
	}
	
	@PostMapping("deleteM")
    public String deleteCompView(@RequestParam("songID") Integer songID) {
        service.deleteMusic(songID);
        return "delete-comp";
    }
}
