package com.naliev.restaurantrating.controller;

import com.naliev.restaurantrating.model.User;
import com.naliev.restaurantrating.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import java.util.List;

import static com.naliev.restaurantrating.util.ValidationUtil.checkNotFound;
import static com.naliev.restaurantrating.util.ValidationUtil.checkNotFoundWithId;

@Controller
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}