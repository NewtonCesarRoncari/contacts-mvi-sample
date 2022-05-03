package com.picpay.desafio.android.utils

interface Mapper<S, T> {
    fun map(source: S): T
}