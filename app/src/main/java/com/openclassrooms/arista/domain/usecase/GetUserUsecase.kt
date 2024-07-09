package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class GetUserUsecase @Inject constructor(private val userRepository: UserRepository) {
     suspend fun execute(): User? {
        return userRepository.getAllUsers().mapNotNull {

                 it.firstOrNull()
        }.firstOrNull()
    }
}