package com.seis739.AbsoluteUnit.repositories;

import com.seis739.AbsoluteUnit.model.Exercise;
import com.seis739.AbsoluteUnit.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoritesRepo extends JpaRepository<Favorites, Long> {

    void deleteExerciseById(Long id);  //Potentially change to deleteExercisebyId

    //Optional in the event we want to return something besides the Employee class that may not exist
    Optional<Favorites> findExerciseById(Long id);

}