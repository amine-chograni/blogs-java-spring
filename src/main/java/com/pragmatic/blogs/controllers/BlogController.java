package com.pragmatic.blogs.controllers;

import com.pragmatic.blogs.dtos.BlogDTO;
import com.pragmatic.blogs.services.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<BlogDTO> createBlog(@PathVariable Long userId,
                                              @RequestBody BlogDTO dto) {
        BlogDTO saved = blogService.createBlog(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/search")
    public Page<BlogDTO> searchBlogs(@RequestParam String keyword,
                                     @PageableDefault(size = 5) Pageable pageable) {
        return blogService.searchBlogs(keyword, pageable);
    }
}

