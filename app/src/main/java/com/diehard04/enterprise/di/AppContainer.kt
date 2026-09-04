package com.diehard04.enterprise.di

import com.diehard04.enterprise.data.repository.AuthRepositoryImpl
import com.diehard04.enterprise.domain.usecase.LoginUseCase

class AppContainer {
    private val authRepository = AuthRepositoryImpl()
    val loginUseCase = LoginUseCase(authRepository)
}