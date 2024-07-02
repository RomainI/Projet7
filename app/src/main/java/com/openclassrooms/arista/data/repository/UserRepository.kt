package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.database.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

class UserRepository(private val userDtoDao: UserDtoDao) {


    // Get all users
    suspend fun getAllUsers(): List<User> {
        return userDtoDao.getAllUsers().first().map {
            User.fromDto(it)
        } // Convert every DTO in Exercise
    }


    // Add a new user
    suspend fun addUser(user: User) {
        userDtoDao.insertUser(user.toDto())
    }


    // Delete an exercise
    suspend fun deleteUser(user: User) {
        // If there is no id, you can raise an exception and catch it in the use case and viewmodel
        user.id?.let {
            userDtoDao.deleteUserById(
                id = user.id,
            )
        }?: throw IllegalArgumentException("User object must have an ID to be deleted")
    }
}