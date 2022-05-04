package com.picpay.desafio.android

import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.presentation.model.UserPresentation

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserPresentation> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}