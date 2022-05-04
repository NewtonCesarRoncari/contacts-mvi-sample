package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.presentation.model.UserPresentation

class GetUsersUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(): List<UserPresentation> = repository.getUsers()
}