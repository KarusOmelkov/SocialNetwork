package com.uspu.Cupcake.Controllers;

import com.uspu.Cupcake.Models.User;
import com.uspu.Cupcake.Repository.UserRepository;
import com.uspu.Cupcake.Service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequestMapping
@Controller
public class MainController {
    @Autowired
    private FeedService feedService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String main(
            @AuthenticationPrincipal User currentUser,
            Model model
    ) {
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("articleSubscriptions", feedService.articleSubscription(currentUser));
        model.addAttribute("countArticle", feedService.getCountArticles());
        return "feed";
    }

    @GetMapping("/search")
    public String search(
            @AuthenticationPrincipal User currentUser,
            Model model
    ){
        model.addAttribute("result", "Результат запроса");
        model.addAttribute("currentUser", currentUser);
        return "search";
    }

    @GetMapping("/find")
    public String searchAuthors(
            @AuthenticationPrincipal User currentUser,
            @RequestParam String request,
            Model model
    ){
        String[] str = request.split(" ");

        if (str.length == 1){
            List<User> findUsers = userRepository.findByFirstNameOrLastName(request, request);
            model.addAttribute("count", findUsers.size());
            model.addAttribute("findUsers", findUsers);
        } else if (str.length == 2) {
            List<User> findUsers = userRepository.findByFirstNameAndLastName(str[0], str[1]);
            List<User> findUsersTwo = userRepository.findByFirstNameAndLastName(str[1], str[0]);
            findUsers.removeAll(findUsersTwo);
            findUsers.addAll(findUsersTwo);
            model.addAttribute("count", findUsers.size());
            model.addAttribute("findUsers", findUsers);
        }

        model.addAttribute("result", "Ни одного автора не найдено");
        model.addAttribute("currentUser", currentUser);
        return "search";
    }


}
