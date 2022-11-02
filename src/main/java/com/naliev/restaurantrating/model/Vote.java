package com.naliev.restaurantrating.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity {

    @Column(name = "date_time", columnDefinition = "timestamp", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Vote v) {
        this(v.getId(), v.getUser(), v.getDateTime(), v.getRestaurant());
    }

    public Vote(Integer id, User user, LocalDateTime dateTime, Restaurant r) {
        super(id);
        this.user = user;
        this.dateTime = dateTime;
        this.restaurant = r;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
