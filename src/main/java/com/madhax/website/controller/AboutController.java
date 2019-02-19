package com.madhax.website.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {

    private final Logger log = LoggerFactory.getLogger(AboutController.class);

    @GetMapping({"", "/"})
    public String about() {
        log.debug("Returning view name.");
        return "about";
    }
}
