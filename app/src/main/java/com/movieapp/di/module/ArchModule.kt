package com.movieapp.di.module

import com.movieapp.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ArchModule {
    val module = module {
        viewModel { MainViewModel(get()) }
    }
}