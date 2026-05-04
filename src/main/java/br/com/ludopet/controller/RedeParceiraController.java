package br.com.ludopet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedeParceiraController {

    @GetMapping("/redeparceira")
    public String redeParceira() {
        return "redeparceira";
    }
}