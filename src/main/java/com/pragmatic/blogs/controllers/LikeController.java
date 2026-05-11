package com.pragmatic.blogs.controllers;

import com.pragmatic.blogs.dtos.LikeDTO;
import com.pragmatic.blogs.services.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{userId}/{blogId}")
    public ResponseEntity<LikeDTO> toggleLike(@PathVariable Long userId,
                                              @PathVariable Long blogId) {
        LikeDTO result = likeService.toggleLike(userId, blogId);
        if (result == null) {
            return ResponseEntity.ok().build(); // unliked
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

