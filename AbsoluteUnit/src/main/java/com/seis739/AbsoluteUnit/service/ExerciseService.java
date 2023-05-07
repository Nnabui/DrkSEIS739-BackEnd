package com.seis739.AbsoluteUnit.service;

import com.seis739.AbsoluteUnit.exceptions.UserNotFoundException;
import com.seis739.AbsoluteUnit.model.Exercise;
import com.seis739.AbsoluteUnit.repositories.ExerciseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepo exerciseRepo;

    @Autowired
    public ExerciseService(ExerciseRepo exerciseRepo) {
        this.exerciseRepo = exerciseRepo;
    }

//    public Exercise addExercise(Exercise exercise) {
//        exercise.setInstructions(UUID.randomUUID().toString());
//        return exerciseRepo.save(exercise);
//    }
    //Saves potential list of exercises
    public List<Exercise> saveEList(List<Exercise> exercise) {
        return exerciseRepo.saveAll(exercise);
    }

    //Returns all available existing exercises
    public List<Exercise> findAllExercises() {
        return exerciseRepo.findAll();
    }

    //Saves one iteration of Exercise (used to be updateEmployee)
    public Exercise saveExercise(Exercise exercise){
            return exerciseRepo.save(exercise);

    }

    public Long countRepository(){
        return exerciseRepo.count();
    }

    //Potentially find exercises by generated id
    public Exercise findExerciseId(Long id){
        return exerciseRepo.findExerciseById(id).orElseThrow(()-> new UserNotFoundException("User by id" + id + "was not found"));
    }

    public void deleteExercise(Long id){
        exerciseRepo.deleteExerciseById(id);
    }
}
