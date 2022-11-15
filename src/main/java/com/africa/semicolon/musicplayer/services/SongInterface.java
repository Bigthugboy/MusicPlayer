package com.africa.semicolon.musicplayer.services;

import com.africa.semicolon.musicplayer.data.model.Song;
import com.africa.semicolon.musicplayer.dto.request.FindRequest;
import com.africa.semicolon.musicplayer.dto.response.FindResponse;
import com.africa.semicolon.musicplayer.exceptions.NoSongFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SongInterface {
   List<String> getSongFileNames();
   void uploadSong(MultipartFile file) throws IOException;
   List<Song>getSong();
   Song findSongById(  FindRequest request) throws NoSongFoundException;
}
