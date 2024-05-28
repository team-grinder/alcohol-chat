package com.alcoholchat.service.implement;

import com.alcoholchat.domain.dto.ImageDTO;
import com.alcoholchat.domain.entity.Image;
import com.alcoholchat.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public Image saveImage(ImageDTO.Request request) {
        return null;
    }

    @Override
    public Image findImage(UUID imageId) {
        return null;
    }

    @Override
    public List<Image> findImageList() {
        return List.of();
    }

    @Override
    public Image updateImage(ImageDTO.Request request) {
        return null;
    }

    @Override
    public void deleteImage(UUID imageId) {

    }
}
