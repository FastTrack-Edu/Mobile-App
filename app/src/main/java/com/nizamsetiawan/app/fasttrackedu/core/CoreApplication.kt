package com.nizamsetiawan.app.fasttrackedu.core

import android.app.Application
import com.nizamsetiawan.app.fasttrackedu.di.modules.KoinModules.listModule
import com.nizamsetiawan.app.fasttrackedu.utils.Prefs
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class CoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Prefs.init(this)
        startKoin {
            androidLogger()
            androidContext(this@CoreApplication)
            modules(listModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}