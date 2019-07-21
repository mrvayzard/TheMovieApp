package com.movieapp.ui.main

import com.movieapp.base.BaseViewModel
import com.movieapp.data.remote.repository.IUserRepository

class MainViewModel(
    private val userRepository: IUserRepository
) : BaseViewModel() {

}