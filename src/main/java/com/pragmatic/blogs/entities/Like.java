package com.pragmatic.blogs.entities;
import jakarta.persistence.*;


@Entity
@Table(name = "Likes")
public class Like {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
}
