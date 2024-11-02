package com.example.elearningapp.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.elearningapp.R
import com.example.elearningapp.databinding.ActivityCourseViewBinding
import com.example.elearningapp.models.Course
import com.example.elearningapp.viewmodel.CourseViewModel
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject

class CourseViewActivity : AppCompatActivity() {
    private var binding: ActivityCourseViewBinding? = null
    private val viewModel: CourseViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCourseViewBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val position = intent.getIntExtra("position", 0)
        val course = viewModel.courses.value?.get(position)
        course?.let { bindCourseData(it) }
        clickListeners()
    }

    private fun clickListeners() {
        binding?.backBtn?.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindCourseData(course: Course) {
        binding?.apply {
            mainText.text = course.category
            courseName.text = course.institute
            startDate.text = course.startDate
            courseDescription.text = course.description
            courseNumber.text = "${course.totalEnroll} already enrolled"
            duration.text = "Duration: ${course.startDate} - ${course.endDate}"
            price.text = "$${course.price}"
        }
    }
}