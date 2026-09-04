package com.diehard04.enterprise.feature.login

import com.diehard04.enterprise.domain.usecase.LoginUseCase

interface LoginDependencies {
    val loginUseCase: LoginUseCase
}