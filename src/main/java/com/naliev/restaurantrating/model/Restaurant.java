package com.naliev.restaurantrating.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id")
    private List<Dish> menu;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName());
    }

    public Restaurant(Integer id, String name, Dish ... menu) {
        super(id, name);
        this.menu = Arrays.asList(menu);
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }
}
