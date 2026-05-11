package com.pragmatic.blogs.repositories;

import com.pragmatic.blogs.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndBlogId(Long userId, Long blogId);
}

