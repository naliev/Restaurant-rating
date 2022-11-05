package com.naliev.restaurantrating.repository.jpa;

import com.naliev.restaurantrating.model.Restaurant;
import com.naliev.restaurantrating.repository.RestaurantRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant r, int userId) {
        if (!r.isNew()) {
            return null;
        }
        em.persist(r);
        return r;
    }

    @Override
    @Transactional
    public Restaurant update(Restaurant r, int userId) {
        if (r.isNew()) {
            return null;
        }
        return em.merge(r);
    }

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createQuery("SELECT r FROM Restaurant r", Restaurant.class)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createQuery("DELETE FROM Restaurant r WHERE r.id = ?1")
                .setParameter(1, id)
                .executeUpdate() != 0;
    }
}
