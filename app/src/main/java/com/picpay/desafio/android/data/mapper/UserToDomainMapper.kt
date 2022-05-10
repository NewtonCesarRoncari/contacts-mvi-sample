package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.UserDomain
import com.picpay.desafio.android.utils.Mapper

class UserToDomainMapper : Mapper<UserResponse, UserDomain> {

    override fun map(source: UserResponse): UserDomain {
        requireNotNull(source.id)
        requireNotNull(source.name)
        requireNotNull(source.username)

        return UserDomain(
            id = source.id,
            img = source.img,
            name = source.name,
            username = source.username
        )
    }
}
