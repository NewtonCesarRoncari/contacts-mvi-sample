package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.UserRemoteDataSource
import com.picpay.desafio.android.domain.mapper.UserToPresentationMapper
import com.picpay.desafio.android.domain.model.UserDomain
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.presentation.model.UserPresentation

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUsers(): List<UserDomain> {
        return remoteDataSource.getUsers()
    }
}