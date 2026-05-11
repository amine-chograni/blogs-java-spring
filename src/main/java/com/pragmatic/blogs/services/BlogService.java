package com.pragmatic.blogs.services;

import com.pragmatic.blogs.dtos.BlogDTO;
import com.pragmatic.blogs.entities.Blog;
import com.pragmatic.blogs.entities.User;
import com.pragmatic.blogs.repositories.BlogRepository;
import com.pragmatic.blogs.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public BlogService(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    public BlogDTO createBlog(BlogDTO dto, Long userId) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Blog blog = new Blog();
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setCreatedAt(LocalDateTime.now());
        blog.setAuthor(author);

        Blog saved = blogRepository.save(blog);
        return mapToDTO(saved);
    }

    public Page<BlogDTO> searchBlogs(String keyword, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCase(keyword, pageable)
                .map(this::mapToDTO);
    }

    private BlogDTO mapToDTO(Blog blog) {
        BlogDTO dto = new BlogDTO();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setAuthorUsername(blog.getAuthor().getUsername());
        dto.setCreatedAt(blog.getCreatedAt());
        return dto;
    }
}

