package com.spring.springmvc_upload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springmvc_upload.entities.Images;
import com.spring.springmvc_upload.entities.Product;
import com.spring.springmvc_upload.repositories.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public Images saveImage(Images image) {
		return imageRepository.save(image);
	}

	public List<Images> getAllImages() {
		return (List<Images>) imageRepository.findAll();
	}

}
