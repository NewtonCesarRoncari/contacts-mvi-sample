package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.domain.model.UserDomain
import com.picpay.desafio.android.presentation.model.UserPresentation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UserToPresentationMapperTest {

    private val mapper by lazy {
        UserToPresentationMapper()
    }

    @Test
    fun `Give success when run map`() {
        // When
        val result = mapper.map(
            UserDomain(img = "path", name = "Linus", id = 42, username = "Torvalds")
        )

        // Then
        assertTrue(result is UserPresentation)
        assertEquals("path", result.img)
        assertEquals("Linus", result.name)
        assertEquals(42, result.id)
        assertEquals("Torvalds", result.username)
    }
}