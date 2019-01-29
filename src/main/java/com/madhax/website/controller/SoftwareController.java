package com.madhax.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SoftwareController {

    @GetMapping("/software")
    public String software() {
        return "software";
    }
}
