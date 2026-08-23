package com.diehard04.enterprise.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diehard04.enterprise.data.repository.AuthRepositoryImpl
import com.diehard04.enterprise.domain.repository.AuthRepository
import com.diehard04.enterprise.domain.usecase.LoginUseCase

class LoginViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val repository: AuthRepository = AuthRepositoryImpl()
            val loginUseCase = LoginUseCase(repository)
            return LoginViewModel(loginUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}