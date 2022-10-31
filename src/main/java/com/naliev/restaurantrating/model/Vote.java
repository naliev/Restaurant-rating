package com.naliev.restaurantrating.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity {
    private Integer userId;
    private LocalDateTime dateTime;
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Vote v) {
        this(v.getId(), v.getUserId(), v.getDateTime(), v.getRestaurant());
    }

    public Vote(Integer id, Integer userId, LocalDateTime dateTime, Restaurant r) {
        super(id);
        this.userId = userId;
        this.dateTime = dateTime;
        this.restaurant = r;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
