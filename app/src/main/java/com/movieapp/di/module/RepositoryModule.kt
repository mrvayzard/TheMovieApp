package com.movieapp.di.module

import com.movieapp.data.remote.repository.IUserRepository
import com.movieapp.data.remote.repository.UserRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        single { UserRepositoryImpl(get(), get()) } bind IUserRepository::class
    }
}