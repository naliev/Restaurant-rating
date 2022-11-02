package com.naliev.restaurantrating.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "id")
    private List<Dish> menu;

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
}
