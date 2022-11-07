package com.africa.semicolon.musicplayer.services;

import com.africa.semicolon.musicplayer.data.model.Song;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SongInterfaceImpl implements SongInterface{
    @Override
    public void addMusic(MultipartFile file) {
        Song song = new Song();



    }
}
