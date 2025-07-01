package com.poc.webplayer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPlayerController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
