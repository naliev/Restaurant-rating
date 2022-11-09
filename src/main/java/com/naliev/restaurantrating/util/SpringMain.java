package com.naliev.restaurantrating.util;

import com.naliev.restaurantrating.model.Restaurant;
import com.naliev.restaurantrating.model.Role;
import com.naliev.restaurantrating.model.User;
import com.naliev.restaurantrating.model.Vote;
import com.naliev.restaurantrating.web.SecurityUtil;
import com.naliev.restaurantrating.web.restaurant.RestaurantRestController;
import com.naliev.restaurantrating.web.user.UserRestController;
import com.naliev.restaurantrating.web.vote.VoteRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            UserRestController userRestController = appCtx.getBean(UserRestController.class);
            RestaurantRestController restaurantRestController = appCtx.getBean(RestaurantRestController.class);
            VoteRestController voteRestController = appCtx.getBean(VoteRestController.class);

            User u = userRestController.create(new User(null, "userName", "email4@mail.ru", "password", Role.ADMIN));
            SecurityUtil.setAuthUserId(u.getId());
            Restaurant rProxy = restaurantRestController.create(new Restaurant(null, "Created restaurant"));
            Vote vProxy = voteRestController.create(u.getId(), rProxy.getId());
            System.out.println(voteRestController.getUserVotes(u.getId()));
        }
    }
}
