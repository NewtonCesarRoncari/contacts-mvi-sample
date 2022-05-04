package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.data.mapper.UserToDomainMapper
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.domain.model.UserDomain

class UserRemoteDataSourceImpl(
    private val service: PicPayService
) : UserRemoteDataSource {

    private val mapper = UserToDomainMapper()

    override suspend fun getUsers(): List<UserDomain> {
        return service.getUsers().map { userResponse ->
            mapper.map(userResponse)
        }
    }
}