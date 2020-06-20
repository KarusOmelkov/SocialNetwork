package com.uspu.Cupcake.Models;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String authorName;

    private String message;

    private Long articleID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    public Comment() {
    }

    public Comment(String message, User user, Long articleID, String authorName) {
        this.message = message;
        this.author = user;
        this.articleID = articleID;
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getArticleID() {
        return articleID;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }
}
