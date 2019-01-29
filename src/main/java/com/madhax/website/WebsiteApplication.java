package com.madhax.website;

import com.madhax.website.domain.User;
import com.madhax.website.service.ArticleService;
import com.madhax.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebsiteApplication {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            User adminUser = new User("Admin", "testP@ss", true);
            userService.saveUser(adminUser);
            User myUser = new User("MADHAX", "testP@ss", true);
            myUser.setFirstName("James");
            myUser.setLastName("Cathcart");
            userService.saveUser(myUser);
        };
    }
}

