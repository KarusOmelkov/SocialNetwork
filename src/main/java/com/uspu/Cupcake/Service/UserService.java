package com.uspu.Cupcake.Service;

import com.uspu.Cupcake.Models.Article;
import com.uspu.Cupcake.Models.User;
import com.uspu.Cupcake.Repository.ArticleRepository;
import com.uspu.Cupcake.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public ArrayList<Article> article(User currentUser){
        ArrayList<Long> articleId = new ArrayList<>();

        Iterable<Article> articles = articleRepository.findByAuthor(currentUser);
        for (Article a : articles){
            articleId.add(a.getId());
        }

        Collections.sort(articleId, Collections.reverseOrder());
        ArrayList<Article> article = new ArrayList<>();
        for (int i = 0; i < articleId.size(); i++) {
            Optional<Article> art = articleRepository.findById(articleId.get(i));
            art.ifPresent(article::add);
        }

        return article;
    }
}
