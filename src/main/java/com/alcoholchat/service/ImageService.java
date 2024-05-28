package com.alcoholchat.service;

import com.alcoholchat.domain.dto.ImageDTO;
import com.alcoholchat.domain.entity.Image;

import java.util.List;
import java.util.UUID;

public interface ImageService {
    // create
    Image saveImage(ImageDTO.Request request);
    // read
    Image findImage(UUID imageId);
    List<Image> findImageList();
    // update
    Image updateImage(ImageDTO.Request request);
    // delete
    void deleteImage(UUID imageId);
}
