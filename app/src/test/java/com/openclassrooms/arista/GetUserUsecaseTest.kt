package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class GetUserUsecaseTest {


    @Mock
    private lateinit var userRepository: UserRepository


    private lateinit var getUserUseCase: GetUserUsecase


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getUserUseCase = GetUserUsecase(userRepository)
    }




    @After
    fun tearDown() {
        Mockito.framework().clearInlineMocks()
    }




    @Test
    fun `when repository returns user, use case should return them`() = runBlocking {
        // Arrange
        val flowFakeUser = flowOf(
            listOf(
            User(
                name = "FakeName",
                id = 12,
                password = "FakePassword",
                email = "fake@email.com"

        )))
        Mockito.`when`(userRepository.getAllUsers()).thenReturn(flowFakeUser)


        // Act
        val result = getUserUseCase.execute()


        // Assert
        assertEquals(flowFakeUser.first().first(), result)
    }


    @Test
    fun `when repository returns empty list, use case should return empty list`() = runBlocking {
        // Arrange
        Mockito.`when`(userRepository.getAllUsers()).thenReturn(flowOf( emptyList()))


        // Act
        val result = getUserUseCase.execute()


        // Assert
        assertTrue(result == null)
    }


}