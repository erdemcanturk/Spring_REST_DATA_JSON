package com.example.demo.Repository;


import com.example.demo.model.Album;
import com.example.demo.model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotosRepository extends JpaRepository<Photos, Long> {

    List<Photos> findAllByAlbum(Album album);

}