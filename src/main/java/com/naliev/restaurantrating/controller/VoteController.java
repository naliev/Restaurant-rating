package com.naliev.restaurantrating.controller;

import com.naliev.restaurantrating.model.Vote;
import com.naliev.restaurantrating.repository.VoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import java.util.List;

import static com.naliev.restaurantrating.util.ValidationUtil.checkNotFoundWithId;

@Controller
public class VoteController {
    private final VoteRepository repository;

    public VoteController(VoteRepository voteRepository) {
        this.repository = voteRepository;
    }

    public Vote create(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, userId, restaurantId);
    }

    public Vote update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        return checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.getId());
    }

    public void delete(int id, int userId, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, userId, restaurantId), id);
    }

    public Vote get(int id, int userId, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, userId, restaurantId), id);
    }

    public List<Vote> getUserVotes(int userId) {
        return repository.getUserVotes(userId);
    }
}
