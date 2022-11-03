package com.naliev.restaurantrating.util;

import com.naliev.restaurantrating.controller.RestaurantController;
import com.naliev.restaurantrating.controller.UserController;
import com.naliev.restaurantrating.controller.VoteController;
import com.naliev.restaurantrating.model.Restaurant;
import com.naliev.restaurantrating.model.Role;
import com.naliev.restaurantrating.model.User;
import com.naliev.restaurantrating.model.Vote;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            UserController userController = appCtx.getBean(UserController.class);
            RestaurantController restaurantController = appCtx.getBean(RestaurantController.class);
            VoteController voteController = appCtx.getBean(VoteController.class);

            User u = userController.create(new User(null, "userName", "email4@mail.ru", "password", Role.ADMIN));
            System.out.println();

            Restaurant r = restaurantController.create(new Restaurant(null, "Created restaurant"), u.getId());

            Vote v = new Vote(null, u, LocalDateTime.now(), r);
            Vote vProxy = voteController.create(v, v.getUser().getId(), v.getRestaurant().getId());
            System.out.println(voteController.getUserVotes(u.getId()));
        }
    }
}
