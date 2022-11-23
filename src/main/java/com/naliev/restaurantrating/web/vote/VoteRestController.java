package com.naliev.restaurantrating.web.vote;

import com.naliev.restaurantrating.model.Restaurant;
import com.naliev.restaurantrating.model.User;
import com.naliev.restaurantrating.model.Vote;
import com.naliev.restaurantrating.repository.VoteRepository;
import com.naliev.restaurantrating.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class VoteRestController {
    private static final Logger log = LoggerFactory.getLogger(VoteRestController.class);
    private final VoteRepository repository;

    @PersistenceContext
    private EntityManager em;

    public VoteRestController(VoteRepository voteRepository) {
        this.repository = voteRepository;
    }

    public Vote create(int userId, int restaurantId) {
        log.info("create by user {} for restaurant {}", userId, restaurantId);
        Restaurant restaurant = em.getReference(Restaurant.class, restaurantId);
        User user = em.getReference(User.class, userId);
        Vote newVote = new Vote(null, user, LocalDate.now(), restaurant);
        return repository.save(newVote);
    }

    public void update(int userId, int restaurantId) {
        log.info("update by user {} for restaurant {}", userId, restaurantId);
        if (LocalTime.now().isAfter(LocalTime.of(11,0))) {
            throw new IllegalStateException("User tried to second vote after 11:00 PM, but present time is " +
                    LocalTime.now().format(DateTimeFormatter.ISO_INSTANT));
        }
        Restaurant restaurant = em.getReference(Restaurant.class, restaurantId);
        Vote todayVote = repository.getByUserAndDate(userId, LocalDate.now()).orElseThrow(
                () -> new IllegalArgumentException("Vote by user: " + userId + " and restaurant: " + restaurantId + "not found")
        );
        if (todayVote.getRestaurant().getId() != restaurantId) {
            todayVote.setRestaurant(restaurant);
            em.merge(todayVote);
        }
    }

    public void delete(int userId, int id) {
        log.info("delete vote with id {} for user {}", id, userId);
        repository.deleteById(id);
    }

    public Vote get(int userId, int id) {
        log.info("get with id {} for the user {}", id, userId);
        if (em.find(Vote.class, id) == null) {
            throw new NotFoundException("Vote with id: " + id + " not found");
        }
        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Vote by user: " + userId + "not found"));
    }

    public List<Vote> getUserVotes(int userId) {
        log.info("get all votes for user {}", userId);
        return repository.getAllByUser(userId);
    }
}
