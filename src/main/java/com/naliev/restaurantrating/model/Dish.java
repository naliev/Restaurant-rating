package com.naliev.restaurantrating.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 100000)
    @NotBlank
    int price;

    public Dish() {
    }

    public Dish(Dish d) {
        this(d.getId(), d.getName(), d.getPrice());
    }

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
