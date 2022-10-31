package com.naliev.restaurantrating.repository.inmemory;

import com.naliev.restaurantrating.model.User;
import com.naliev.restaurantrating.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class inMemoryUserRepository extends inMemoryBaseRepository<User> implements UserRepository {

    @Override
    public User getByEmail(String email) {
        return map.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }
}
