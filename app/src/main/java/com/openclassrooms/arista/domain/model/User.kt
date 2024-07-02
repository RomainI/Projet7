package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.UserDto

data class User(val id: Long? = null,var name: String, var email: String, var password:String)

{
    fun toDto(): UserDto {
        return UserDto(
            id=this.id ?: 0,
            name = this.name,
            email = this.email,
            password = this.password
        )
    }

    companion object {
        fun fromDto(dto: UserDto): User {
            return User(
                id = dto.id.takeIf { it != 0L },
                name = dto.name,
                email = dto.email,
                password = dto.password
            )
        }
    }
}