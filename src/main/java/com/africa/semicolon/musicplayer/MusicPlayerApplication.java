package com.africa.semicolon.musicplayer;

import com.africa.semicolon.musicplayer.data.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicPlayerApplication {



    public static void main(String[] args) {
        SpringApplication.run(MusicPlayerApplication.class, args);

    }

}
