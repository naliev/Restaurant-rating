package com.naliev.restaurantrating.model;

import java.util.List;

public class Restaurant extends AbstractNamedEntity {
    private List<Dish> menu;
    private List<Vote> rating;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName());
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public List<Vote> getRating() {
        return rating;
    }

    public void setRating(List<Vote> rating) {
        this.rating = rating;
    }
}
