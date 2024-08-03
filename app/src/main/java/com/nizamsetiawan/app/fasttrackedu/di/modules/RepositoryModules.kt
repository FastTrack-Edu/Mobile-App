package com.nizamsetiawan.app.fasttrackedu.di.modules
import com.nizamsetiawan.app.fasttrackedu.repository.AppRepository
import org.koin.dsl.module

val repositoryModules = module {
    single { AppRepository(get(), get()) }
}