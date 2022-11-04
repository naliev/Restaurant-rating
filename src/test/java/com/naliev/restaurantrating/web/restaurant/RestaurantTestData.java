package com.naliev.restaurantrating.web.restaurant;

import com.naliev.restaurantrating.model.Restaurant;
import com.naliev.restaurantrating.web.MatcherFactory;

import java.util.Arrays;
import java.util.List;

import static com.naliev.restaurantrating.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator();

    public static final int RESTAURANT1_ID = START_SEQ + 3;
    public static final int RESTAURANT2_ID = START_SEQ + 4;
    public static final int NOT_FOUND = 10;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Tasty and that's it");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "Praga restaurant");

    public static final List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2);

    public static Restaurant getNew() {
        return new Restaurant(null, "New restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT2_ID, "Praga restaurant updated");
    }
}
