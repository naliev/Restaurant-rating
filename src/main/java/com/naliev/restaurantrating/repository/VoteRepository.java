package com.naliev.restaurantrating.repository;

import com.naliev.restaurantrating.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote v, int userId, int restaurantId);

    Vote update(Vote v, int userId, int restaurantId);

    Vote get(int id, int userId, int restaurantId);

    List<Vote> getUserVotes(int userId);

    boolean delete(int id, int userId, int restaurantId);
}
