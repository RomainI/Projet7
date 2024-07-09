package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
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
class GetAllSleepsUseCaseTest {


    @Mock
    private lateinit var sleepRepository: SleepRepository


    private lateinit var getAllSleepsUseCase: GetAllSleepsUseCase


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAllSleepsUseCase = GetAllSleepsUseCase(sleepRepository)
    }




    @After
    fun tearDown() {
        Mockito.framework().clearInlineMocks()
    }




    @Test
    fun `when repository returns sleeps, use case should return them`() = runBlocking {
        // Arrange
        val fakeSleeps = listOf(
            Sleep(
                startTime = LocalDateTime.now(),
                duration = 10,
                quality = 1
            ),
            Sleep(
                startTime = LocalDateTime.now().plusHours(1),
                duration = 45,
                quality = 2
            )
        )
        Mockito.`when`(sleepRepository.getAllSleeps()).thenReturn(fakeSleeps)


        // Act
        val result = getAllSleepsUseCase.execute()


        // Assert
        assertEquals(fakeSleeps, result)
    }


    @Test
    fun `when repository returns empty list, use case should return empty list`() = runBlocking {
        // Arrange
        Mockito.`when`(sleepRepository.getAllSleeps()).thenReturn(emptyList())


        // Act
        val result = getAllSleepsUseCase.execute()


        // Assert
        assertTrue(result.isEmpty())
    }


}