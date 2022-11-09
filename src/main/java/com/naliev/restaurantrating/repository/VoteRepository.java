package com.naliev.restaurantrating.repository;

import com.naliev.restaurantrating.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.date DESC")
    List<Vote> getAllByUser(@Param("userId") int userId);

    @Query("SELECT v FROM Vote V WHERE v.user.id=:userId AND v.date=:date")
    Optional<Vote> getByUserAndDate(@Param("userId")int userId, @Param("date")LocalDate date);
}
