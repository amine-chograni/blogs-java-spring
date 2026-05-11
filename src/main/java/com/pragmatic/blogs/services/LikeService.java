package com.pragmatic.blogs.services;

import com.pragmatic.blogs.dtos.LikeDTO;
import com.pragmatic.blogs.entities.Blog;
import com.pragmatic.blogs.entities.Like;
import com.pragmatic.blogs.entities.User;
import com.pragmatic.blogs.repositories.BlogRepository;
import com.pragmatic.blogs.repositories.LikeRepository;
import com.pragmatic.blogs.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public LikeService(LikeRepository likeRepository,
                       UserRepository userRepository,
                       BlogRepository blogRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    public LikeDTO toggleLike(Long userId, Long blogId) {
        Optional<Like> existing = likeRepository.findByUserIdAndBlogId(userId, blogId);

        if (existing.isPresent()) {
            likeRepository.delete(existing.get());
            return null; // means "unliked"
        }

        User user = userRepository.findById(userId).orElseThrow();
        Blog blog = blogRepository.findById(blogId).orElseThrow();

        Like like = new Like();
        like.setUser(user);
        like.setBlog(blog);

        Like saved = likeRepository.save(like);
        return mapToDTO(saved);
    }

    private LikeDTO mapToDTO(Like like) {
        LikeDTO dto = new LikeDTO();
        dto.setId(like.getId());
        dto.setUserUsername(like.getUser().getUsername());
        dto.setBlogId(like.getBlog().getId());
        return dto;
    }
}

