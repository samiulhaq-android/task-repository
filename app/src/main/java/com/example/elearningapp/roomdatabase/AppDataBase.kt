package com.example.elearningapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elearningapp.models.Course

@Database(entities = [Course::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartedCourseBao

}
