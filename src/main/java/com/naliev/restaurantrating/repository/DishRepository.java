package com.naliev.restaurantrating.repository;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DishRepository {
}
