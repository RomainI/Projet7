package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.database.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class UserRepository(private val userDtoDao: UserDtoDao) {


    // Get all users
    fun getAllUsers(): Flow<List<User>> {
        return userDtoDao.getAllUsers().map { userList ->
            userList.map { dto ->
                User.fromDto(dto)
            }
        }
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