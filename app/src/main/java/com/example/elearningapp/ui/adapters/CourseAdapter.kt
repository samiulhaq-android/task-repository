package com.example.elearningapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elearningapp.R
import com.example.elearningapp.models.Course
import com.squareup.picasso.Picasso

class CourseAdapter(
    private val courses: List<Course>,
    private val onEnrollClick: (Course, position: Int) -> Unit,
    private val onAddToCartClick: (Course) -> Unit,
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_item, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount(): Int = courses.size

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val courseName: TextView = itemView.findViewById(R.id.courseTitle)
        private val courseInstitute: TextView = itemView.findViewById(R.id.instituteName)
        private val courseCategory: TextView = itemView.findViewById(R.id.certificationType)
        private val addToCart: TextView = itemView.findViewById(R.id.addToCart)
        private val courseType: ImageView = itemView.findViewById(R.id.courseImage)

        fun bind(course: Course) {
            courseName.text = course.name
            courseInstitute.text = course.institute
            courseCategory.text = course.category
            val assetUri = "file:///android_asset/${course.icon}"
            Picasso.get()
                .load(assetUri)
                .placeholder(R.drawable.placeholder_logo)
                .error(R.drawable.placeholder_logo)
                .into(courseType)

            itemView.setOnClickListener {
                onEnrollClick(course, adapterPosition)
            }
            addToCart.setOnClickListener {
                addToCart.text = itemView.context.getString(R.string.added)
                onAddToCartClick(course)
            }
        }
    }
}
