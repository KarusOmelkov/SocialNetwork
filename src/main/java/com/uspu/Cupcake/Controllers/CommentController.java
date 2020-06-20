package com.uspu.Cupcake.Controllers;

import com.uspu.Cupcake.Models.Article;
import com.uspu.Cupcake.Models.Comment;
import com.uspu.Cupcake.Models.User;
import com.uspu.Cupcake.Repository.ArticleRepository;
import com.uspu.Cupcake.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/article/{id}")
    public String createComment(
            @AuthenticationPrincipal User user,
            @RequestParam String message,
            @PathVariable(value = "id") long id
    ){
        String authorName = user.getFirstName()+" "+user.getLastName();
        Comment comment = new Comment(message, user, id, authorName);
        commentRepository.save(comment);

        return "redirect:/article/{id}";
    }

}
