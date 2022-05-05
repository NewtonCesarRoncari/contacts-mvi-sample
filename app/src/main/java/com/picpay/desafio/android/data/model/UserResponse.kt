package com.picpay.desafio.android.data.model

data class UserResponse(
    val img: String? = null,
    val name: String = "",
    val id: Int = 0,
    val username: String = "",
) {
    override fun toString(): String {
        return "UserResponse(img=$img, name='$name', id=$id, username='$username')"
    }
}