package com.pragmatic.blogs.dtos;

import java.time.LocalDateTime;

public class CommentDTO {
    private Long id;
    private String content;
    private String userUsername;
    private Long blogId;
    private LocalDateTime createdAt;
}
