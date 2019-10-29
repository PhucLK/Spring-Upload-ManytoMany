package com.spring.springmvc_upload.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.springmvc_upload.entities.Images;

@Repository
public interface ImageRepository extends CrudRepository<Images, Integer> {

}
