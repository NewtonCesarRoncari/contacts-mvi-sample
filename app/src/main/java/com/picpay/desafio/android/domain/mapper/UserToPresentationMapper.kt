package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.domain.model.UserDomain
import com.picpay.desafio.android.presentation.model.UserPresentation
import com.picpay.desafio.android.utils.Mapper

class UserToPresentationMapper : Mapper<UserDomain, UserPresentation> {

    override fun map(source: UserDomain): UserPresentation {
        return UserPresentation(
            id = source.id,
            img = source.img,
            name = source.name,
            username = source.username
        )
    }
}
