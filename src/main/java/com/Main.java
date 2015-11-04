package com;

import com.user.CustomAuthentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
public class Main {

    @RequestMapping("/test")
    public String test() {
        CustomAuthentication customAuthentication = (CustomAuthentication)
                SecurityContextHolder.getContext().getAuthentication();
        return customAuthentication.getId() + " " + customAuthentication.getFirstName()
                + " " + customAuthentication.getLastName();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
