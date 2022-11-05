package com.naliev.restaurantrating.web.restaurant;

import com.naliev.restaurantrating.model.Restaurant;
import com.naliev.restaurantrating.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static com.naliev.restaurantrating.web.restaurant.RestaurantTestData.*;
import static org.junit.Assert.assertThrows;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantRestControllerTest {

    @Autowired
    private RestaurantRestController controller;

    @Test
    public void create() {
        Restaurant created = controller.create(getNew());
        Restaurant newRestaurant = getNew();
        int newId = created.getId();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(controller.get(newId), newRestaurant);
    }

    @Test
    public void update() {
        Restaurant updatedRestaurant = getUpdated();
        controller.update(updatedRestaurant, updatedRestaurant.getId());
        RESTAURANT_MATCHER.assertMatch(controller.get(RESTAURANT2_ID), getUpdated());
    }

    @Test
    public void get() {
        RESTAURANT_MATCHER.assertMatch(controller.get(RESTAURANT1_ID), restaurant1);
    }

    @Test
    public void getAll() {
        RESTAURANT_MATCHER.assertMatch(controller.getAll(), restaurant1, restaurant2);
    }

    @Test
    public void delete() {
        controller.delete(RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> controller.get(RESTAURANT1_ID));
    }
}