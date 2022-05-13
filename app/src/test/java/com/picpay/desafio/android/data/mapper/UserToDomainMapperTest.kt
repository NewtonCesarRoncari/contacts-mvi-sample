package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.UserDomain
import org.junit.Assert.*

import org.junit.Test

class UserToDomainMapperTest {

    private val mapper by lazy {
        UserToDomainMapper()
    }

    @Test
    fun `Give success when run map`() {
        // When
        val result = mapper.map(
            UserResponse(img = "path", name = "Linus", id = 42, username = "Torvalds")
        )

        // Then
        assertTrue(result is UserDomain)
        assertEquals("path", result.img)
        assertEquals("Linus", result.name)
        assertEquals(42, result.id)
        assertEquals("Torvalds", result.username)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null id`() {
        // When
        mapper.map(UserResponse(img = "path", name = "Linus", id = null, username = "Torvalds"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null name`() {
        // When
        mapper.map(UserResponse(img = "path", name = null, id = 42, username = "Torvalds"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should error when run map with null username`() {
        // When
        mapper.map(UserResponse(img = "path", name = "Linus", id = 42, username = null))
    }
}