package com.uspu.Cupcake.Controllers;

import com.uspu.Cupcake.Models.Article;
import com.uspu.Cupcake.Models.User;
import com.uspu.Cupcake.Repository.ArticleRepository;
import com.uspu.Cupcake.Repository.CommentRepository;
import com.uspu.Cupcake.Repository.UserRepository;
import com.uspu.Cupcake.Service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Controller
public class FeedController {
    @Autowired
    private FeedService feedService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/feed/user_id{user}")
    public String feed(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model
    ){
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("articleSubscriptions", feedService.articleSubscription(user));
        model.addAttribute("countArticle", feedService.getCountArticles());

        return "feed";
    }

    @GetMapping("/feed/user_id{user}/{category}")
    public String feed(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @PathVariable String category,
            Model model
    ){
        switch (category){
            case "blog":model.addAttribute("category", "Личный блог");break;
            case "sport":model.addAttribute("category", "Спорт");break;
            case "food":model.addAttribute("category", "Еда");break;
            case "travel":model.addAttribute("category", "Путешествия");break;
            case "music":model.addAttribute("category", "Музыка");break;
            case "beautiful":model.addAttribute("category", "Красота");break;
            case "literature":model.addAttribute("category", "Литература");break;
            case "film":model.addAttribute("category", "Кино");break;
            case "humor":model.addAttribute("category", "Юмор");break;
            case "games":model.addAttribute("category", "Игры");break;
        }

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("articleSubscriptions", feedService.articleSubscription(user));
        model.addAttribute("countArticle", feedService.getCountArticles());
        return "feedCategory";
    }

}
