package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import javax.inject.Inject

class GetUserUsecase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun execute(): User {
        val users = userRepository.getAllUsers()
        if(users.isEmpty()){
            throw NoSuchElementException("user list empty")
        }
        return userRepository.getAllUsers().first()
    }
}