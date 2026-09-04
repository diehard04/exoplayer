package com.diehard04.enterprise

import android.app.Application
import com.diehard04.enterprise.di.AppContainer
import com.diehard04.enterprise.di.LoginDependenciesImpl
import com.diehard04.enterprise.feature.login.LoginDependencies
import com.diehard04.enterprise.feature.login.LoginDependenciesProvider

class EnterpriseApplication : Application(), LoginDependenciesProvider {

    val appContainer = AppContainer()
    override val loginDependencies: LoginDependencies = LoginDependenciesImpl(appContainer)
}