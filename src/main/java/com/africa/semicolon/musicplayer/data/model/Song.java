package com.africa.semicolon.musicplayer.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Song {
    @Id
    private String id;
    private String fileName;
    private String artist;
    private String title;
    private Boolean isFavourite;

}
