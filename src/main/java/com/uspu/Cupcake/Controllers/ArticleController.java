package com.uspu.Cupcake.Controllers;

import com.google.common.collect.Iterables;
import com.uspu.Cupcake.Models.Article;
import com.uspu.Cupcake.Models.Comment;
import com.uspu.Cupcake.Models.User;
import com.uspu.Cupcake.Repository.ArticleRepository;
import com.uspu.Cupcake.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/editor")
    public String editorArticle(
            @AuthenticationPrincipal User user,
            Model model
    ){
        model.addAttribute("currentUser", user);
        return"articleEditor";
    }

    @PostMapping("/editor")
    public String AddArticle(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String category,
            @RequestParam String annotation,
            @RequestParam String text
    ){
        Article article = new Article(title, category, annotation, text, user);
        articleRepository.save(article);
        return "redirect:/article/"+article.getId();
    }

    @GetMapping("/article/{id}")
    public String article(
            @AuthenticationPrincipal User user,
            @PathVariable(value = "id") long id,
            Model model
    ){
        if(!articleRepository.existsById(id)){
            return "redirect:/profile";
        }

        Optional<Article> article = articleRepository.findById(id);
        ArrayList<Article> res = new ArrayList<>();
        article.ifPresent(res::add);
        model.addAttribute("article", res);

        Iterable<Comment> comments = commentRepository.findByArticleID(id);
        model.addAttribute("comments", comments);
        model.addAttribute("commentCount", Iterables.size(comments));

        model.addAttribute("currentUser", user);
        return "article";
    }


}
