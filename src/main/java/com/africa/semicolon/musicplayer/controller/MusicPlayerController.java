package com.africa.semicolon.musicplayer.controller;


import com.africa.semicolon.musicplayer.data.model.Song;
import com.africa.semicolon.musicplayer.dto.request.FindRequest;
import com.africa.semicolon.musicplayer.dto.response.ApiResponse;
import com.africa.semicolon.musicplayer.exceptions.MusicPlayerExceptions;
import com.africa.semicolon.musicplayer.exceptions.NoSongFoundException;
import com.africa.semicolon.musicplayer.services.SongInterfaceImpl;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/musicplayer")
public class MusicPlayerController {
    private final SongInterfaceImpl songInterface;

    @GetMapping("/findAll")
    public ResponseEntity<List<Song>> getSong() {
        return ResponseEntity.ok(songInterface.getSong());
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findSOngById(@RequestBody FindRequest request) {
        try {
            songInterface.findSongById(request);
            ApiResponse apiResponse = ApiResponse.builder()
                    .isSuccessful(true)
                    .data("SONG found")
                    .result(1)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (MusicPlayerExceptions e) {
            ApiResponse apiResponse = ApiResponse.builder()
                    .isSuccessful(false)
                    .data("SONG not found")
                    .result(0)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

    }
}
