package com.example.elearningapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elearningapp.R
import com.example.elearningapp.models.Course
import com.squareup.picasso.Picasso

class CartAdapter(
    private val cartedCourses: List<Course>,
    private val onEnrollClick: (Course, position: Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartedCourses[position])
    }

    override fun getItemCount(): Int = cartedCourses.size

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val courseName: TextView = itemView.findViewById(R.id.courseTitle)
        private val courseEndDate: TextView = itemView.findViewById(R.id.endDate)
        private val instituteName: TextView = itemView.findViewById(R.id.instituteName)
        private val courseCategory: TextView = itemView.findViewById(R.id.certificationType)
        private val courseIcon: ImageView = itemView.findViewById(R.id.courseImage)
        private val startNow: TextView = itemView.findViewById(R.id.startNow)

        fun bind(course: Course) {
            courseName.text = course.name
            courseEndDate.text = course.endDate
            instituteName.text = course.institute
            courseCategory.text = course.category
            val assetUri = "file:///android_asset/${course.icon}"
            Picasso.get()
                .load(assetUri)
                .placeholder(R.drawable.placeholder_logo)
                .error(R.drawable.placeholder_logo)
                .into(courseIcon)
            itemView.setOnClickListener {
                onEnrollClick(course, adapterPosition)
            }
        }
    }
}
