package com.africa.semicolon.musicplayer.services;



import com.africa.semicolon.musicplayer.data.model.Song;
import com.africa.semicolon.musicplayer.data.repository.SongRepository;
import com.africa.semicolon.musicplayer.dto.request.FindRequest;
import com.africa.semicolon.musicplayer.dto.response.FindResponse;
import com.africa.semicolon.musicplayer.exceptions.NoSongFoundException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;


import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SongInterfaceImpl implements SongInterface {
    private SongRepository repository;
    private final AmazonS3 space;

    public SongInterfaceImpl() {
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIA5PJFUXBDHNCAR5EP","OOA13T26pQywgkFiOWgR6b07EtfaQkUP19xqv6KX"));

        space = AmazonS3ClientBuilder
                .standard()
                .withCredentials(credentialsProvider)
//                .withRegion(Regions.AP_EAST_1)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("vpce-031c8c78e43e41fbf", "US East (N. Virginia) us-east-1"))
                .build();

    }

    @Override
    public List<String> getSongFileNames() {
        ListObjectsV2Result result = space.listObjectsV2("musicplus");
        List<S3ObjectSummary> object = result.getObjectSummaries();
//        it the details bout the object we have
        return object.stream()
                .map(S3ObjectSummary::getKey).collect(Collectors.toList());
//        we are streaming which of the object summary, and we are making a list out of it, and we return the list

    }

    @Override
    public void uploadSong(MultipartFile file) throws IOException {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        space.putObject(new PutObjectRequest("musicplayer", file.getOriginalFilename(), file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
// it takes the multipartfile and create a new metadata object,set some basic info like the content type, and we use to space to put the object into our digital ocean space, and input stream is actual data of the file
// when file is being uploaded it upload to private.
    }

    @Override
    public List<Song> getSong() {
        return repository.findAll();
    }

    @Override
    public Song findSongById(FindRequest request) throws NoSongFoundException {
        Optional<Song> song = repository.findById(request.getId());
        if (song.isPresent()) {
            FindResponse response = new FindResponse();
            response.setMessage("successful");
            return song.get();
        }

        throw new NoSongFoundException("Song not found");
    }
}




