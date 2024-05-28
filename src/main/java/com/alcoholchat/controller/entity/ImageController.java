package com.alcoholchat.controller.entity;

import com.alcoholchat.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
}
