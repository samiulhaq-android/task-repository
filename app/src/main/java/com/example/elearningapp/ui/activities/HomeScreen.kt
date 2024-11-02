package com.example.elearningapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.elearningapp.R
import com.example.elearningapp.databinding.ActivityMainBinding
import com.example.elearningapp.ui.adapters.CourseAdapter
import com.example.elearningapp.viewmodel.CourseViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class HomeScreen : AppCompatActivity() {
    private val viewModel: CourseViewModel by inject()
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.courses.observe(this) { courses ->
            binding?.recyclerViewCourses?.adapter = CourseAdapter(courses,{course, position ->
                startActivity(Intent(this, CourseViewActivity::class.java).putExtra("position", position))
            },{course ->
                viewModel.addCourseToCart(course)
            })
        }

        viewModel.loadCourses() // Load courses from JSON
        clicklistener()
    }

    private fun clicklistener() {
        binding?.apply {
            cartBtn.setOnClickListener {
                startActivity(Intent(this@HomeScreen, CartedActivity::class.java))
            }
        }
    }
}