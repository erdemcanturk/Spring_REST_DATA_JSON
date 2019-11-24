package com.example.demo.Repository;

import com.example.demo.model.Album;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Album findById(long l);

    List<Album> findAllByUser(User user);

}
