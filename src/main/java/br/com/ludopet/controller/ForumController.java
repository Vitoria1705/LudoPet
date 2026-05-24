package br.com.ludopet.controller;

import br.com.ludopet.model.ForumPost;
import br.com.ludopet.repository.ForumPostRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForumController {

    @Autowired
    private ForumPostRepository repository;

    @GetMapping("/forum")
    public String forum(Model model) {

        model.addAttribute(
                "forumPost",
                new ForumPost()
        );

        return "forum";
    }

    @PostMapping("/forum")
    public String salvarDuvida(
            ForumPost forumPost
    ) {

        repository.save(forumPost);

        return "redirect:/forum";
    }
}