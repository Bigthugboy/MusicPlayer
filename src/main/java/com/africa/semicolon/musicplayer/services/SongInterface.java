package com.africa.semicolon.musicplayer.services;

import org.springframework.web.multipart.MultipartFile;

public interface SongInterface {
    void addMusic (MultipartFile file);
}
