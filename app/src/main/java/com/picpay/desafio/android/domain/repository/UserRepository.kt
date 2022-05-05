package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.UserDomain

interface UserRepository {

    suspend fun getUsers(): List<UserDomain>
}