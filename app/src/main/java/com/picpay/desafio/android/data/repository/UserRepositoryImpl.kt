package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.UserRemoteDataSource
import com.picpay.desafio.android.domain.mapper.UserToPresentationMapper
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.presentation.model.UserPresentation

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    private val mapper = UserToPresentationMapper()

    override suspend fun getUsers(): List<UserPresentation> {
        return remoteDataSource.getUsers().map { userDomain ->
            mapper.map(userDomain)
        }
    }
}