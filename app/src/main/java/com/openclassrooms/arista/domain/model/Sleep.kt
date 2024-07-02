package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.SleepDto
import com.openclassrooms.arista.data.entity.UserDto
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class Sleep(@JvmField var startTime: LocalDateTime, var duration: Int, var quality: Int, val id: Long? = null)

{
    fun toDto(): SleepDto {
        return SleepDto(
            id=this.id ?: 0,
            startTime = this.startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            duration = this.duration,
            quality = this.quality
        )
    }

    companion object {
        fun fromDto(dto: SleepDto): Sleep {
            return Sleep(
                id = dto.id.takeIf { it != 0L },
                startTime = Instant.ofEpochMilli(dto.startTime).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                duration = dto.duration,
                quality = dto.quality
            )
        }
    }
}