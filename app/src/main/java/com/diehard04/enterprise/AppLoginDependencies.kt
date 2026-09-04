package com.diehard04.enterprise

import com.diehard04.enterprise.data.repository.AuthRepositoryImpl
import com.diehard04.enterprise.domain.usecase.LoginUseCase
import com.diehard04.enterprise.feature.login.LoginDependencies

class AppLoginDependencies: LoginDependencies {

    override fun provideLoginUseCase(): LoginUseCase {
        val repository   = AuthRepositoryImpl()
        return LoginUseCase(repository)
    }
}