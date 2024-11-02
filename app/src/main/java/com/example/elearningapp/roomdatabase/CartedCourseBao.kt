package com.example.elearningapp.roomdatabase

import androidx.room.*
import com.example.elearningapp.models.Course

@Dao
interface CartedCourseBao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCourseToCart(cartedCourse: Course)

    @Query("SELECT * FROM carted_courses")
    suspend fun getCartedCourses(): List<Course>

    @Query("DELETE FROM carted_courses WHERE courseId = :courseId")
    suspend fun removeCourseFromCart(courseId: String)
}
