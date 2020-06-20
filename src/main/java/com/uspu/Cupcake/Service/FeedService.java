package com.uspu.Cupcake.Service;

import com.uspu.Cupcake.Models.Article;
import com.uspu.Cupcake.Models.User;
import com.uspu.Cupcake.Repository.ArticleRepository;
import com.uspu.Cupcake.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class FeedService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    ArrayList<Article> articleSubscriptions;

    public ArrayList<Article> articleSubscription(User currentUser){
        ArrayList<Long> articleId = new ArrayList<>();

        Set<User> subscriptions = currentUser.getSubscriptions();
        for (User author : subscriptions) {
            Iterable<Article> articles = articleRepository.findByAuthor(author);
            for (Article a : articles){
                articleId.add(a.getId());
            }
        }

        Collections.sort(articleId, Collections.reverseOrder());
        articleSubscriptions = new ArrayList<>();
        for (int i = 0; i < articleId.size(); i++) {
            Optional<Article> art = articleRepository.findById(articleId.get(i));
            art.ifPresent(articleSubscriptions::add);
        }

        return articleSubscriptions;
    }

    public long getCountArticles(){
        return articleSubscriptions.size();
    }

}
