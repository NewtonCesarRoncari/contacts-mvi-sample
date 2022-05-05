package com.picpay.desafio.android.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.datasource.UserRemoteDataSource
import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.UserDomain
import com.picpay.desafio.android.presentation.model.UserPresentation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryImplTest {

    private val dataSource: UserRemoteDataSource = mock()

    private val repository by lazy {
        UserRepositoryImpl(dataSource)
    }

    private val users = listOf(
        UserDomain(img = "", name = "Linus", id = 42, username = "Torvalds"),
        UserDomain(img = "", name = "Ada", id = 42, username = "Lovelace"),
        UserDomain(img = "", name = "Kent", id = 42, username = "Beck")
    )

    @Test
    fun `When get users from repository should return success`() = runBlockingTest {
        // Given
        whenever(dataSource.getUsers()).thenReturn(users)

        // When
        val result = repository.getUsers()

        // Then
        verify(dataSource).getUsers()
        assertEquals(3, result.size)
        assertTrue(result.first() is UserDomain)
    }
}