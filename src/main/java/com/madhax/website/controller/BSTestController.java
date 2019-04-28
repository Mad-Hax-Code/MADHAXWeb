package com.madhax.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bstest")
public class BSTestController {

    @GetMapping
    public String showBSTest() {
        return "bstest";
    }
}
