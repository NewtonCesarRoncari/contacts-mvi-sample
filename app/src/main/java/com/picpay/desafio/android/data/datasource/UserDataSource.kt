package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.domain.model.UserDomain

interface UserDataSource {

    suspend fun getUsers(): List<UserDomain>
}