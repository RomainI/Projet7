package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetUserUsecase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun execute(): User? {
        var users : List<User>
        while (true){
        users = userRepository.getAllUsers()

        if (users.isNotEmpty()) {
            return users.first()
        } else {
            delay(500)
        }
    }
    }
}