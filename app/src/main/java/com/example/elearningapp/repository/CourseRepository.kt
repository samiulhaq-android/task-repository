package com.example.elearningapp.repository

import android.content.Context
import com.example.elearningapp.models.Course
import com.example.elearningapp.roomdatabase.CartedCourseBao
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
class CourseRepository(private val context: Context, private val cartDao: CartedCourseBao) {

    suspend fun loadCoursesFromJson(): List<Course> = withContext(Dispatchers.IO) {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())  // Add the KotlinJsonAdapterFactory here
            .build()

        val type = Types.newParameterizedType(List::class.java, Course::class.java)
        val adapter = moshi.adapter<List<Course>>(type)

        val inputStream: InputStream = context.assets.open("courses.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        val coursesJson = adapter.fromJson(json) ?: emptyList()

        // Map CourseJson to Course for Room storage
        coursesJson.map {
            Course(
                courseId = it.courseId,
                institute = it.institute,
                name = it.name,
                category = it.category,
                description = it.description,
                totalEnroll = it.totalEnroll,
                startDate = it.startDate,
                endDate = it.endDate,
                price = it.price,
                icon = it.icon
            )
        }
    }

    suspend fun addCourseToCart(course: Course) = cartDao.addCourseToCart(course)

    suspend fun getCartedCourses(): List<Course> = cartDao.getCartedCourses()

    suspend fun removeCourseFromCart(courseId: String) = cartDao.removeCourseFromCart(courseId)
}

