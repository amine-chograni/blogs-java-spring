package com.pragmatic.blogs.services;

import com.pragmatic.blogs.dtos.CommentDTO;
import com.pragmatic.blogs.entities.Blog;
import com.pragmatic.blogs.entities.Comment;
import com.pragmatic.blogs.entities.User;
import com.pragmatic.blogs.repositories.BlogRepository;
import com.pragmatic.blogs.repositories.CommentRepository;
import com.pragmatic.blogs.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository,
                          BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    public CommentDTO addComment(CommentDTO dto, Long userId, Long blogId) {
        User user = userRepository.findById(userId).orElseThrow();
        Blog blog = blogRepository.findById(blogId).orElseThrow();

        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user);
        comment.setBlog(blog);

        Comment saved = commentRepository.save(comment);
        return mapToDTO(saved);
    }

    private CommentDTO mapToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setUserUsername(comment.getUser().getUsername());
        dto.setBlogId(comment.getBlog().getId());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}

