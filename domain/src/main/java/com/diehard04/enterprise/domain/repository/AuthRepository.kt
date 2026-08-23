package com.diehard04.enterprise.domain.repository
import com.diehard04.enterprise.domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String
    ): Result<User>
}