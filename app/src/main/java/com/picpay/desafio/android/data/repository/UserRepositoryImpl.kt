package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.UserRemoteDataSource
import com.picpay.desafio.android.domain.model.UserDomain
import com.picpay.desafio.android.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUsers(): List<UserDomain> {
        return remoteDataSource.getUsers()
    }
}