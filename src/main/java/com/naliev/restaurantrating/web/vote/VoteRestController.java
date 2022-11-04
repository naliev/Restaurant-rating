package com.naliev.restaurantrating.web.vote;

import com.naliev.restaurantrating.model.Vote;
import com.naliev.restaurantrating.repository.VoteRepository;
import com.naliev.restaurantrating.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.naliev.restaurantrating.util.ValidationUtil.assureIdConsistent;
import static com.naliev.restaurantrating.util.ValidationUtil.checkNew;

@Controller
public class VoteRestController {
    private static final Logger log = LoggerFactory.getLogger(VoteRestController.class);
    private final VoteRepository repository;

    public VoteRestController(VoteRepository voteRepository) {
        this.repository = voteRepository;
    }

    public Vote create(Vote vote, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        checkNew(vote);
        log.info("create {} for user {}", vote, userId);
        return repository.save(vote, userId, restaurantId);
    }

    public void update(Vote vote, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(vote, userId);
        log.info("update {} for user {}", vote, userId);
        repository.update(vote, userId, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("delete vote with id {} by user {}", id, userId);
        repository.delete(id, userId, restaurantId);
    }

    public Vote get(int id, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("get vote by id {} by user {}", id, userId);
        return repository.get(id, userId, restaurantId);
    }

    public List<Vote> getUserVotes(int userId) {
        log.info("get {} user's votes", userId);
        return repository.getUserVotes(userId);
    }
}
