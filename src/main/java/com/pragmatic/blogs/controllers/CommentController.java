package com.pragmatic.blogs.controllers;

import com.pragmatic.blogs.dtos.CommentDTO;
import com.pragmatic.blogs.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{userId}/{blogId}")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long userId,
                                                 @PathVariable Long blogId,
                                                 @RequestBody CommentDTO dto) {
        CommentDTO saved = commentService.addComment(dto, userId, blogId);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}

