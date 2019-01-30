package com.madhax.website;

import com.madhax.website.domain.Article;
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

            User myUser = new User(
                    "admin",
                    "$2a$04$C/mOkKfXtOhKjhnUUrwp3OcWzLHJqkGzYpV1oys.MBPXc9M8soAQ6",
                    "USER");
            myUser.setFirstName("James");
            myUser.setLastName("Cathcart");
            userService.saveUser(myUser);

            Article article1 = new Article(
                    "Example Article 1 Title",
                    "Quisque volutpat condimentum velit. Class aptent taciti sociosqu ad litora " +
                    "torquent per conubia nostra, per inceptos himenaeos. Nam nec ante. Sed lacinia, " +
                    "urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis " +
                    "turpis. Nulla facilisi. Ut fringilla. Suspendisse potenti. Nunc feugiat mi a tellus " +
                    "consequat imperdiet. Vestibulum sapien. Proin quam. Etiam ultrices.",
                    myUser);
            articleService.saveArticle(article1);

            Article article2 = new Article(
                    "Example Article 1 Title",
                    "Quisque volutpat condimentum velit. Class aptent taciti sociosqu ad litora " +
                            "torquent per conubia nostra, per inceptos himenaeos. Nam nec ante. Sed lacinia, " +
                            "urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis " +
                            "turpis. Nulla facilisi. Ut fringilla. Suspendisse potenti. Nunc feugiat mi a tellus " +
                            "consequat imperdiet. Vestibulum sapien. Proin quam. Etiam ultrices.",
                    myUser);
            articleService.saveArticle(article2);

            Article article3 = new Article(
                    "Example Article 1 Title",
                    "Quisque volutpat condimentum velit. Class aptent taciti sociosqu ad litora " +
                            "torquent per conubia nostra, per inceptos himenaeos. Nam nec ante. Sed lacinia, " +
                            "urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis " +
                            "turpis. Nulla facilisi. Ut fringilla. Suspendisse potenti. Nunc feugiat mi a tellus " +
                            "consequat imperdiet. Vestibulum sapien. Proin quam. Etiam ultrices.",
                    myUser);
            articleService.saveArticle(article3);

            Article article4 = new Article(
                    "Example Article 1 Title",
                    "Quisque volutpat condimentum velit. Class aptent taciti sociosqu ad litora " +
                            "torquent per conubia nostra, per inceptos himenaeos. Nam nec ante. Sed lacinia, " +
                            "urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis " +
                            "turpis. Nulla facilisi. Ut fringilla. Suspendisse potenti. Nunc feugiat mi a tellus " +
                            "consequat imperdiet. Vestibulum sapien. Proin quam. Etiam ultrices.",
                    myUser);
            articleService.saveArticle(article4);

        };
    }
}

