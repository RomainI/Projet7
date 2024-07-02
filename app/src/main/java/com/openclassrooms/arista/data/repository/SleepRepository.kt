package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.FakeApiService
import com.openclassrooms.arista.database.dao.ExerciseDtoDao
import com.openclassrooms.arista.database.dao.SleepDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.first

class SleepRepository(private val sleepDtoDao: SleepDtoDao) {

    suspend fun getAllSleeps(): List<Sleep> {
        return sleepDtoDao.getAllSleeps().first().map {
            Sleep.fromDto(it)
        } // Convert every DTO in Sleeps
    }


    // Add a new exercise
    suspend fun addSleep(sleep: Sleep) {
        sleepDtoDao.insertSleep(sleep.toDto())
    }


    // Delete an exercise
    suspend fun deleteExercise(sleep: Sleep) {
        // If there is no id, you can raise an exception and catch it in the use case and viewmodel

            sleep.id?.let {
                sleepDtoDao.deleteSleepById(
                    id = sleep.id,
                )
            }?: throw IllegalArgumentException("Sleep object must have an ID to be deleted")

    }
}