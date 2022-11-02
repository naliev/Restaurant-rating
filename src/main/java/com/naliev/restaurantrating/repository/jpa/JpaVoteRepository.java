package com.naliev.restaurantrating.repository.jpa;

import com.naliev.restaurantrating.model.User;
import com.naliev.restaurantrating.model.Vote;
import com.naliev.restaurantrating.repository.VoteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew()) {
            return null;
        }
        User reference = em.getReference(User.class, userId);
        vote.setUser(reference);
        em.persist(vote);
        return vote;
    }

    @Override
    @Transactional
    public Vote update(Vote vote, int userId, int restaurantId) {
        if (vote.isNew()) {
            return null;
        }
        return em.merge(vote);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId, int restaurantId) {
        return em.createQuery("DELETE FROM Vote v WHERE v.id=?1 AND v.user=?2")
                .setParameter(1, id)
                .setParameter(2, userId)
                .executeUpdate() != 0;
    }

    @Override
    public Vote get(int id, int userId, int restaurantId) {
        Vote v = em.find(Vote.class, id);
        return v != null && v.getUser().getId() == userId ? v : null;
    }

    @Override
    public List<Vote> getUserVotes(int userId) {
        return em.createQuery("SELECT v FROM Vote v WHERE v.user.id=?1", Vote.class)
                .setParameter(1, userId)
                .getResultList();
    }
}