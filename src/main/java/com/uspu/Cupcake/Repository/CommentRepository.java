package com.uspu.Cupcake.Repository;

import com.uspu.Cupcake.Models.Article;
import com.uspu.Cupcake.Models.Comment;
import com.uspu.Cupcake.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Iterable<Comment> findByArticleID(Long id);
}
