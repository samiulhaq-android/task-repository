package com.example.elearningapp.di

import androidx.room.Room
import com.example.elearningapp.repository.CourseRepository
import com.example.elearningapp.roomdatabase.AppDatabase
import com.example.elearningapp.viewmodel.CourseViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    //single { AppDatabase.getDatabase(get()) }
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "app_database").build() }
    single { get<AppDatabase>().cartDao() }
    single { CourseRepository(get(), get()) }
    single { CourseViewModel(get(), get()) }
}
