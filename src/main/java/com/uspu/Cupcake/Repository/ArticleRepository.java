package com.uspu.Cupcake.Repository;

import com.uspu.Cupcake.Models.Article;
import com.uspu.Cupcake.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    Iterable<Article> findByAuthor(User user);
}
