package com.picpay.desafio.android.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.presentation.model.UserPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class ListContactsViewModel(
    private val useCase: GetUsersUseCase
) : ViewModel() {

    private val _onRequisitionError = MutableLiveData<String?>()
    val onRequisitionError: LiveData<String?> get() = _onRequisitionError

    private val _contacts = MutableLiveData<List<UserPresentation>>()
    val contacts: LiveData<List<UserPresentation>> get() = _contacts

    fun getContacts() {
        viewModelScope.launch(Dispatchers.IO) {

            kotlin.runCatching {
                useCase()
            }.onSuccess { contacts ->
                _contacts.postValue(contacts)
            }.onFailure { error ->
                onError(error)
            }
        }
    }

    private fun onError(error: Throwable) {
        val messageError: String = when (error) {
            is HttpException -> "code: ${error.code()}, ${
                error.response()!!.errorBody()!!.string()
            }"
            is SocketTimeoutException -> "Timeout: failed to connect to server"
            is ConnectException -> "Network: failed to connect to server, verify your connexion"
            else -> "Unexpected error"
        }
        _onRequisitionError.postValue(messageError)
    }
}