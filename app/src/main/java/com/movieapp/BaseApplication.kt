package com.movieapp

import android.app.Application
import com.facebook.stetho.Stetho
import com.movieapp.di.module.ArchModule
import com.movieapp.di.module.DatabaseModule
import com.movieapp.di.module.NetworkModule
import com.movieapp.di.module.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

        val moduleList = listOf(
            ArchModule.module,
            DatabaseModule.module,
            NetworkModule.module,
            RepositoryModule.module
        )
        startKoin {
            androidContext(this@BaseApplication)
            modules(moduleList)
        }

    }
}