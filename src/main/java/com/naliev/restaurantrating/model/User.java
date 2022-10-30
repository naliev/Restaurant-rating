package com.naliev.restaurantrating.model;

public class User extends AbstractNamedEntity {
    public User() {
    }

    public User(Integer id, String name) {
        super(id, name);
    }
}
