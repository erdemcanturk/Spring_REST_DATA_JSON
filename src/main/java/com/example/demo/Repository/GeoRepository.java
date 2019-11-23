package com.example.demo.Repository;

import com.example.demo.model.Geo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoRepository extends CrudRepository<Geo,Long> {



}
