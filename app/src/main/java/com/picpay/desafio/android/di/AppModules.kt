package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.datasource.UserRemoteDataSource
import com.picpay.desafio.android.data.datasource.UserRemoteDataSourceImpl
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.data.retrofit.HttpClient
import com.picpay.desafio.android.data.retrofit.RetrofitClient
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.presentation.viewmodel.ListContactsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModules = module {
    factory<UserRemoteDataSource> { UserRemoteDataSourceImpl(service = get()) }
    factory<UserRepository> { UserRepositoryImpl(remoteDataSource = get()) }
}

val domainModules = module {
    factory { GetUsersUseCase(repository = get()) }
}

val networkModules = module {
    single { RetrofitClient(application = androidContext()).newInstance() }
    single { HttpClient(get()) }
    factory { get<HttpClient>().create(PicPayService::class.java) }
}

val presentationModules = module {
    viewModel { ListContactsViewModel(useCase = get()) }
}