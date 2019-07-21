package com.movieapp.di.module

import androidx.room.Room
import com.movieapp.data.local.database.AppDatabase
import com.movieapp.data.local.preferences.PreferencesHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {
    val module = module {
        //Preferences helper
        single {
            PreferencesHelper(androidContext())
        }

        // App Database
        single { Room.databaseBuilder(androidApplication(), AppDatabase::class.java, AppDatabase.NAME).build() }

        //Dao instances
        single { get<AppDatabase>().userDao() }
    }
}