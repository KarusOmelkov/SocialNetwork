package com.uspu.Cupcake.Controllers;

import com.uspu.Cupcake.Models.Article;
import com.uspu.Cupcake.Models.User;
import com.uspu.Cupcake.Repository.ArticleRepository;
import com.uspu.Cupcake.Repository.UserRepository;
import com.uspu.Cupcake.Service.FeedService;
import com.uspu.Cupcake.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{user}")
    public String profile(
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser,
            Model model
    ){
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("user", user);
        model.addAttribute("articles", userService.article(currentUser));
        return "profile";
    }

    @GetMapping("/user/subscribe/{user}")
    public String subscribe(
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser
    ){
        user.getSubscribers().add(currentUser);
        userRepository.save(user);

        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/user/unsubscribe/{user}")
    public String unsubscribe(
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser
    ){
        user.getSubscribers().remove(currentUser);
        userRepository.save(user);

        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/user/{type}/{user}")
    public String sub(
            @PathVariable User user,
            @PathVariable String type,
            @AuthenticationPrincipal User currentUser,
            Model model
    ){
        if("subscriptions".equals(type)){
            model.addAttribute("sub", user.getSubscriptions());
            model.addAttribute("type", "Подписки");
            model.addAttribute("count", user.getSubscriptions().size());
        } else if("subscribers".equals(type)) {
            model.addAttribute("sub", user.getSubscribers());
            model.addAttribute("type", "Подписчики");
            model.addAttribute("count", user.getSubscribers().size());
        }
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("user", user);
        return "sub";
    }

    @GetMapping("/user/edit")
    public String userSettings(
            @AuthenticationPrincipal User currentUser,
            Model model
    ){
        model.addAttribute("aboutMe", currentUser.getAboutMe());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("saved", false);
        return "userSettings";
    }

    @PostMapping("/edit")
    public String saveAboutMe(
            @AuthenticationPrincipal User currentUser,
            @RequestParam String aboutMe,
            Model model
    ){
        currentUser.setAboutMe(aboutMe);
        userRepository.save(currentUser);
        model.addAttribute("aboutMe", currentUser.getAboutMe());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("saved", true);
        return "userSettings";
    }
}
