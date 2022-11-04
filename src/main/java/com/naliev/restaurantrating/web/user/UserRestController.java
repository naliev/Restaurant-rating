package com.naliev.restaurantrating.web.user;

import com.naliev.restaurantrating.model.User;
import com.naliev.restaurantrating.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import java.util.List;

import static com.naliev.restaurantrating.util.ValidationUtil.checkNew;
import static com.naliev.restaurantrating.util.ValidationUtil.checkNotFoundWithId;

@Controller
public class UserRestController {
    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);
    private final UserRepository repository;

    public UserRestController(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        checkNew(user);
        log.info("create {}", user);
        return repository.save(user);
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.delete(id);
    }

    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.getByEmail(email);
    }

    public List<User> getAll() {
        log.info("getAll");
        return repository.getAll();
    }
}