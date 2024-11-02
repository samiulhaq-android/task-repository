package com.example.elearningapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.elearningapp.models.Course
import com.example.elearningapp.repository.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(private val application: Application, private val courseRepository: CourseRepository) : AndroidViewModel(application) {

    val courses = MutableLiveData<List<Course>>()
    val cartedCourses = MutableLiveData<List<Course>>()

    fun loadCourses() {
        viewModelScope.launch {
            courses.value = courseRepository.loadCoursesFromJson()
        }
    }

    fun loadCart() {
        viewModelScope.launch {
            cartedCourses.value = courseRepository.getCartedCourses()
        }
    }

    fun addCourseToCart(course: Course) {
        viewModelScope.launch {
            courseRepository.addCourseToCart(course)
            loadCart()
        }
    }

    fun removeCourseFromCart(courseId: String) {
        viewModelScope.launch {
            courseRepository.removeCourseFromCart(courseId)
            loadCart()
        }
    }
}
