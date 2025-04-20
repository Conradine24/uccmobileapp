package com.example.uccitmobileapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uccitmobileapp.adapter.CourseAdapter
import com.example.uccitmobileapp.data.Course
import com.example.uccitmobileapp.databinding.ActivityCoursesBinding
import com.example.uccitmobileapp.db.CourseRepository

class CoursesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoursesBinding
    private lateinit var courseRepository: CourseRepository
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        courseRepository = CourseRepository(this)
        setupRecyclerView()
        setupFab()
    }

    private fun setupRecyclerView() {
        val courseList = courseRepository.getAllCourses()
        courseAdapter = CourseAdapter(courseList) { course ->
            // Handle course item click
            val intent = Intent(this, CourseDetailActivity::class.java).apply {
                putExtra(EXTRA_COURSE_ID, course.id)
            }
            startActivity(intent)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CoursesActivity)
            adapter = courseAdapter
        }
    }

    private fun setupFab() {
        binding.fabEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("hod.it@ucc.edu.jm"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "IT Department Inquiry")

            try {
                startActivity(Intent.createChooser(intent, "Send email using..."))
            } catch (ex: android.content.ActivityNotFoundException) {
                // Handle case where no email app is available
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_COURSE_ID = "extra_course_id"
    }
}