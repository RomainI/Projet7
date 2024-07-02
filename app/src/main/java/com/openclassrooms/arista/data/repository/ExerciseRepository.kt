package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.database.dao.ExerciseDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.first

class ExerciseRepository(private val exerciseDtoDao: ExerciseDtoDao) {


    // Get all exercises
    suspend fun getAllExercises(): List<Exercise> {
        return exerciseDtoDao.getAllExercises().first().map {
            Exercise.fromDto(it)
        } // Convert every DTO in Exercise
    }


    // Add a new exercise
    suspend fun addExercise(exercise: Exercise) {
        exerciseDtoDao.insertExercise(exercise.toDto())
    }


    // Delete an exercise
    suspend fun deleteExercise(exercise: Exercise) {
        // If there is no id, you can raise an exception and catch it in the use case and viewmodel
        exercise.id?.let {
            exerciseDtoDao.deleteExerciseById(
                id = exercise.id,
            )
        }?: throw IllegalArgumentException("Exercice object must have an ID to be deleted")
    }
}