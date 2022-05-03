package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.presentation.model.UserPresentation

interface UserRepository {

    suspend fun getUsers(): List<UserPresentation>
}