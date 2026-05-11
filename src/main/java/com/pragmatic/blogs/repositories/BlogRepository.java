package com.pragmatic.blogs.repositories;

import com.pragmatic.blogs.entities.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    List<Blog> findByAuthorUsername(String username);
}

