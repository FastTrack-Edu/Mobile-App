package com.nizamsetiawan.app.fasttrackedu.repository

import com.nizamsetiawan.app.fasttrackedu.source.local.LocalDataSource
import com.nizamsetiawan.app.fasttrackedu.source.remote.RemoteDataSource



class AppRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {


}