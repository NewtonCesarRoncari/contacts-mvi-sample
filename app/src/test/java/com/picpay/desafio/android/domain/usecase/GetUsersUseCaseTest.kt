package com.picpay.desafio.android.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.domain.model.UserDomain
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.presentation.model.UserPresentation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private val repository: UserRepository = mock()

    private val useCase by lazy {
        GetUsersUseCase(repository)
    }

    private val users = listOf(
        UserDomain(img = "", name = "Linus", id = 42, username = "Torvalds"),
        UserDomain(img = "", name = "Ada", id = 42, username = "Lovelace"),
        UserDomain(img = "", name = "Kent", id = 42, username = "Beck")
    )

    @Test
    fun `When get users from use case should return success`() = runBlockingTest {
        // Given
        whenever(repository.getUsers()).thenReturn(users)

        // When
        val result = useCase()

        // Then
        verify(repository).getUsers()
        assertEquals(3, result.size)
        assertTrue(result.first() is UserPresentation)
    }
}