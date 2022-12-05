package com.naliev.restaurantrating.web.dish;

import com.naliev.restaurantrating.model.Dish;
import com.naliev.restaurantrating.repository.DishRepository;
import com.naliev.restaurantrating.web.restaurant.RestaurantRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@CacheConfig(cacheNames = "dishes")
public class DishRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final DishRepository repository;

    @PersistenceContext
    private EntityManager em;

    public DishRestController(DishRepository repository) {
        this.repository = repository;
    }

    public Dish get(int dishId, int restaurantId) {
        log.info("get dish {} for restaurant {}", dishId, restaurantId);
        Optional<Dish> dish = repository.get(dishId, restaurantId);
        return dish.orElseThrow(() -> new IllegalArgumentException("Dish with id: " + dishId + " in a restaurant " + restaurantId + "not found"));
    }

    public List<Dish> getAll(int restaurantId) {
        log.info("get all dishes for restaurant {}", restaurantId);
        return repository.getAll(restaurantId);
    }

    @Transactional
    @CacheEvict(cacheNames = "dishes", allEntries = true)
    public void delete(int dishId, int restaurantId) {
        log.info("Try to delete dish {} for restaurant {}", dishId, restaurantId);
        if (repository.existsByIdAndRestaurantId(dishId, restaurantId)) {
            Dish dish = em.find(Dish.class, dishId);
            if (dish.getDate().isBefore(LocalDate.now())) {
                throw new IllegalStateException("Cannot delete dish for the past days"); //TODO proper exception;
            }
            repository.deleteExisted(dishId);
            log.info("Successfully deleted dish {} for restaurant {}", dishId, restaurantId);
        } else {
            log.info("Unable to find dish {} for restaurant {}", dishId, restaurantId);
            throw new IllegalArgumentException("Unable to find dish"); //TODO proper exception;
        }
    }
}
