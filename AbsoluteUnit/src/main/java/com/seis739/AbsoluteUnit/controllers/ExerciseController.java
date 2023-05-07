package com.seis739.AbsoluteUnit.controllers;

import com.google.gson.Gson;
import com.seis739.AbsoluteUnit.model.Exercise;
import com.seis739.AbsoluteUnit.model.Favorites;
import com.seis739.AbsoluteUnit.service.ExerciseService;
import com.seis739.AbsoluteUnit.service.FavoritesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")//base URL
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final FavoritesService favoritesService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, FavoritesService favoritesService) {
        this.exerciseService = exerciseService;
        this.favoritesService = favoritesService;
    }

    @GetMapping("/all")//Get request to retrieve data from backend (going to URL/exercise/all)
    public ResponseEntity<List<Exercise>> getAllExercises(){
        List<Exercise> exercises = exerciseService.findAllExercises();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")//Get request to retrieve data from backend
    public ResponseEntity<Exercise> getExerciseById(@PathVariable("id") Long id){
        Exercise exercise = exerciseService.findExerciseId(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @GetMapping("/favorites/all")//Get request to retrieve favorites data from backend (going to URL/exercise/favorites/all)
    public ResponseEntity<List<Favorites>> getAllFavorites(){
        List<Favorites> favorites = favoritesService.findAllFavorites();
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @PostMapping("/favorites/add/{id}")//Get request to retrieve data from backend
    public ResponseEntity<Favorites> addExerciseById(@PathVariable("id") Long id){
        Exercise exercise = exerciseService.findExerciseId(id);

        Gson gson = new Gson();
        String jsonString = gson.toJson(exercise);
        Favorites newExercise = gson.fromJson(jsonString, Favorites.class);

        Favorites newFavorite = favoritesService.saveFavorites(newExercise);
        return new ResponseEntity<>(newFavorite, HttpStatus.OK);
    }
    //Making a change in the backend
//    @PostMapping("/add")
//    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise){
//        Exercise exercise11 = exerciseService.addExercise(exercise);
//        return new ResponseEntity<>(exercise11, HttpStatus.CREATED);
//    }

    @PutMapping("/update")
    public ResponseEntity<Exercise> updateEmployee(@RequestBody Exercise exercise){
        Exercise updateExercise = exerciseService.saveExercise(exercise);
        return new ResponseEntity<>(updateExercise, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable("id") Long id) {
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/favorites/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteFavoriteExercise(@PathVariable("id") Long id) {
        favoritesService.deleteFavorites(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
