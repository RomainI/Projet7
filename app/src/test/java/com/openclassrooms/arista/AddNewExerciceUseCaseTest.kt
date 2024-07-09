package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import junit.framework.TestCase.assertEquals
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
class AddNewExerciseUseCaseTest {

    @Mock
    private lateinit var exerciseRepository: ExerciseRepository

    private lateinit var addNewExerciseUseCase: AddNewExerciseUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        addNewExerciseUseCase = AddNewExerciseUseCase(exerciseRepository)
    }

    @After
    fun tearDown() {
        Mockito.framework().clearInlineMocks()
    }

    @Test
    fun `when adding new exercise, should return success`() = runBlocking {
        // Arrange
        val fakeExercice = Exercise(
            startTime = LocalDateTime.now(),
            duration = 2,
            category = ExerciseCategory.Running,
            intensity = 3
        )
       // Mockito.`when`(exerciseRepository.getAllExercises().first()).thenReturn(newExercise)

        // Act
        addNewExerciseUseCase.execute(fakeExercice)

        // Assert
        Mockito.verify(exerciseRepository).addExercise(fakeExercice)
    }
}