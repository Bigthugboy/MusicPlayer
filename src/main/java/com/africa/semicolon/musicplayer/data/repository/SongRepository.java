package com.africa.semicolon.musicplayer.data.repository;

import com.africa.semicolon.musicplayer.data.model.Song;
import org.springframework.data.
        mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song,String> {
    boolean existsSongByTitle(String title);
}
