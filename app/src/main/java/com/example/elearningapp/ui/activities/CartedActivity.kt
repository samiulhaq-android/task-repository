package com.example.elearningapp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.elearningapp.R
import com.example.elearningapp.databinding.ActivityCartedBinding
import com.example.elearningapp.ui.adapters.CartAdapter
import com.example.elearningapp.ui.adapters.CourseAdapter
import com.example.elearningapp.viewmodel.CourseViewModel
import org.koin.android.ext.android.inject

class CartedActivity : AppCompatActivity() {
    private var binding: ActivityCartedBinding? = null
    private val viewModel: CourseViewModel by inject()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartedBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.cartedCourses.observe(this) { courses ->
            binding?.recyclerViewCourses?.adapter = CartAdapter(courses){course, position ->}
            val total = courses.sumOf { it.price }
            binding?.totalCoursesPrice?.text = "Total Courses Price: $$total"
        }
        viewModel.loadCart()
        binding?.backBtn?.setOnClickListener {
            finish()
        }
    }
}