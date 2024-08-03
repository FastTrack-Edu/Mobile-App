package com.nizamsetiawan.app.fasttrackedu.di.modules
import com.nizamsetiawan.app.fasttrackedu.source.local.LocalDataSource
import com.nizamsetiawan.app.fasttrackedu.source.remote.RemoteDataSource
import com.nizamsetiawan.app.fasttrackedu.source.remote.network.ApiConfig
import com.nizamsetiawan.app.fasttrackedu.utils.Prefs
import org.koin.dsl.module

val AppModules = module {
    single {
        ApiConfig.provideApiService
    }
    single { RemoteDataSource(get()) }
    single { LocalDataSource() }
//    single {
//        Room.databaseBuilder(
//            androidContext(),
//            AppDatabase::class.java,
//            Constant.DB_NAME
//        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
//    }
    single { Prefs.init(get()) }
}