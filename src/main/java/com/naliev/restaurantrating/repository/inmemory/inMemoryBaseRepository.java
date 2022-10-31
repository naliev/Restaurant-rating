package com.naliev.restaurantrating.repository.inmemory;

import com.naliev.restaurantrating.model.AbstractBaseEntity;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import static com.naliev.restaurantrating.model.AbstractBaseEntity.START_SEQ;

public class inMemoryBaseRepository<T extends AbstractBaseEntity> {
    static final AtomicInteger counter = new AtomicInteger(START_SEQ);
    protected ConcurrentMap<Integer, T> map = new ConcurrentHashMap<>();

    public T save(T entity) {
        Objects.requireNonNull(entity, "Entity must not be null");
        if (entity.isNew()) {
            entity.setId(counter.getAndIncrement());
            map.put(entity.getId(), entity);
            return entity;
        }
        return map.computeIfPresent(entity.getId(), (id, oldT) -> entity);
    }

    public boolean delete(int id) {
        return map.remove(id) != null;
    }

    public T get(int id) {
        return map.get(id);
    }

    public Collection<T> getCollection() {
        return map.values();
    }
}
