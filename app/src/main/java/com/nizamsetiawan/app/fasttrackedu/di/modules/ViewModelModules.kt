package com.nizamsetiawan.app.fasttrackedu.di.modules

import com.nizamsetiawan.app.fasttrackedu.views.auth.viewmodels.AuthViewModels
import com.nizamsetiawan.app.fasttrackedu.views.dashboard.viewmodels.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { AuthViewModels(get()) }
    viewModel { DashboardViewModel(get()) }


}