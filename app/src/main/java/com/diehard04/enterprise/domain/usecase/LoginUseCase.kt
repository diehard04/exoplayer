package com.diehard04.enterprise.domain.usecase

import com.diehard04.enterprise.domain.model.User
import com.diehard04.enterprise.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String) = authRepository.login(
        username = username,
        password = password
    )
}