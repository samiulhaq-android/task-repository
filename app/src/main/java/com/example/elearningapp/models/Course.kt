package com.example.elearningapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass


@Entity(tableName = "carted_courses")
@JsonClass(generateAdapter = true)
data class Course(
    @PrimaryKey val courseId: String,
    val institute: String,
    val name: String,
    val category: String,
    val description: String,
    val totalEnroll: Int,
    val startDate: String,
    val endDate: String,
    val price: Double,
    val icon: String
)

