package com.seis739.AbsoluteUnit.service;

import com.seis739.AbsoluteUnit.exceptions.UserNotFoundException;
import com.seis739.AbsoluteUnit.model.Exercise;
import com.seis739.AbsoluteUnit.model.Favorites;
import com.seis739.AbsoluteUnit.repositories.ExerciseRepo;
import com.seis739.AbsoluteUnit.repositories.FavoritesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesService {
    private final FavoritesRepo favoritesRepo;

    @Autowired
    public FavoritesService(FavoritesRepo favoritesRepo) {
        this.favoritesRepo = favoritesRepo;
    }

    //Saves potential list of exercises
    public List<Favorites> saveEList(List<Favorites> favorite) {
        return favoritesRepo.saveAll(favorite);
    }

    //Returns all available existing exercises
    public List<Favorites> findAllFavorites() {
        return favoritesRepo.findAll();
    }

    //Saves one iteration of Exercise (used to be updateEmployee)
    public Favorites saveFavorites(Favorites favorite){
        return favoritesRepo.save(favorite);
    }

    //Potentially find exercises by generated id
    public Favorites findExerciseId(Long id){
        return favoritesRepo.findExerciseById(id).orElseThrow(()-> new UserNotFoundException("User by id" + id + "was not found"));
    }

    public void deleteFavorites(Long id){
        favoritesRepo.deleteExerciseById(id);
    }
}
