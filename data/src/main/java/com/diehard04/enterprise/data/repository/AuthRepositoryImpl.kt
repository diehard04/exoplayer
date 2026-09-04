package com.diehard04.enterprise.data.repository

import com.diehard04.enterprise.domain.model.User
import com.diehard04.enterprise.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class AuthRepositoryImpl: AuthRepository {
    override suspend fun login(
        username: String,
        password: String
    ): Result<User> {
        delay(1000)
        return if (username == "admin" &&
            password == "1234") {
            Result.success(
                User(
                    id = "1001",
                    name = "Android Engineer",
                    email = "android@example.com"
                )
            )
        } else {
            Result.failure(
                Exception("Invalid username or password")
            )
        }
    }
}