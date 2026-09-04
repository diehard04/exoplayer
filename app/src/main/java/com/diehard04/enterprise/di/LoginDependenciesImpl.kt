package com.diehard04.enterprise.di

import com.diehard04.enterprise.domain.usecase.LoginUseCase
import com.diehard04.enterprise.feature.login.LoginDependencies

class LoginDependenciesImpl(private val appContainer: AppContainer): LoginDependencies {
    override val loginUseCase = appContainer.loginUseCase
}