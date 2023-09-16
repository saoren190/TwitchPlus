package com.laioffer.twitch;

import com.laioffer.twitch.db.UserRepository;
import com.laioffer.twitch.db.entity.UserEntity;
import com.laioffer.twitch.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DevelopmentTester implements ApplicationRunner {


    private static final Logger logger = LoggerFactory.getLogger(DevelopmentTester.class);


    private final UserService userService;


    public DevelopmentTester(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(ApplicationArguments args) {
        userService.register("default", "123456", "John", "Smith");
    }
}


