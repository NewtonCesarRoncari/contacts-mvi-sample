package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.mapper.UserToPresentationMapper
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.presentation.model.UserPresentation

class GetUsersUseCase(
    private val repository: UserRepository
) {

    private val mapper = UserToPresentationMapper()

    suspend operator fun invoke(): List<UserPresentation> {
        return repository.getUsers().map { userDomain ->
            mapper.map(userDomain)
        }
    }
}