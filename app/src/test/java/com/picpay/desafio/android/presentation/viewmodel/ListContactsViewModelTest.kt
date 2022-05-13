package com.picpay.desafio.android.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.presentation.model.UserPresentation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class ListContactsViewModelTest {

    @get:Rule
    var instantExecutionRule = InstantTaskExecutorRule()

    private val useCase: GetUsersUseCase = mock(GetUsersUseCase::class.java)

    private val viewModel by lazy { ListContactsViewModel(useCase) }

    private val usersPresentation = listOf(
        UserPresentation(img = "", name = "Linus", id = 42, username = "Torvalds"),
        UserPresentation(img = "", name = "Ada", id = 42, username = "Lovelace"),
        UserPresentation(img = "", name = "Kent", id = 42, username = "Beck")
    )

    @Test
    fun `When get users from viewModel should return success`() = runBlockingTest {
        // Given
        whenever(useCase()).thenReturn(usersPresentation)

        // When
        viewModel.getContacts()

        val result = viewModel.contacts.getOrAwaitValue()

        verify(useCase).invoke()
        Assert.assertEquals(3, result.size)
        Assert.assertTrue(result.first() is UserPresentation)
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserver: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserver.invoke()

        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}