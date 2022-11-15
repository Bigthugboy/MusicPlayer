package com.africa.semicolon.musicplayer.controller;

import com.africa.semicolon.musicplayer.services.SongInterfaceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class IndexController {
    private final SongInterfaceImpl songInterface;
    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("songFileNames",songInterface.getSongFileNames());
//        mvc , model , view, controller
//        model.addAttribute("songFileNames",songInterface.getSongFileNames());
    return "index";
    }
    @PostMapping
    public String handleFileUpload(@RequestParam("file")MultipartFile file) throws IOException {
        songInterface.uploadSong(file);
        return "redirect:/";

    }
}
