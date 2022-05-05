package com.picpay.desafio.android.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.domain.model.UserDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class UserRemoteDataSourceImplTest {

    private val service: PicPayService = mock()

    private val dataSource by lazy {
        UserRemoteDataSourceImpl(service)
    }

    private val usersResponse = listOf(
        UserResponse(img = "", name = "Linus", id = 42, username = "Torvalds"),
        UserResponse(img = "", name = "Ada", id = 42, username = "Lovelace"),
        UserResponse(img = "", name = "Kent", id = 42, username = "Beck")
    )

    @Test
    fun `When get users from data source should return success`() = runBlockingTest {
        // Given
        whenever(service.getUsers()).thenReturn(usersResponse)

        // When
        val result = dataSource.getUsers()

        // Then
        verify(service).getUsers()
        assertEquals(3, result.size)
        assertTrue(result.first() is UserDomain)
    }
}