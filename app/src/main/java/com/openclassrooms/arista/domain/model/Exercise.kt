package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.ExerciseDto
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class Exercise(
    val id: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var category: ExerciseCategory,
    var intensity: Int
)
{
    fun toDto(): ExerciseDto {
        return ExerciseDto(
            id = this.id ?: 0, // Handle nullable ID for new inserts
            startTime = this.startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            duration = this.duration,
            category = this.category.name,
            intensity = this.intensity
        )
    }

    companion object {
        fun fromDto(dto: ExerciseDto): Exercise {
            return Exercise(
                id = dto.id.takeIf { it != 0L },
                startTime = Instant.ofEpochMilli(dto.startTime).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                duration = dto.duration,
                category = ExerciseCategory.valueOf(dto.category),
                intensity = dto.intensity
            )
        }
    }
}
