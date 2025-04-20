package com.example.uccitmobileapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uccitmobileapp.databinding.ActivityCourseDetailBinding
import com.example.uccitmobileapp.db.CourseRepository

class CourseDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseDetailBinding
    private lateinit var courseRepository: CourseRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        courseRepository = CourseRepository(this)

        val courseId = intent.getLongExtra(CoursesActivity.EXTRA_COURSE_ID, -1)
        if (courseId != -1L) {
            displayCourseDetails(courseId)
        } else {
            finish()
        }

        setupFab()
    }

    private fun displayCourseDetails(courseId: Long) {
        val course = courseRepository.getCourseById(courseId)
        course?.let {
            binding.textCourseCode.text = it.code
            binding.textCourseName.text = it.name
            binding.textCourseCredits.text = it.credits.toString()
            binding.textCoursePrerequisites.text = it.prerequisites
            binding.textCourseDescription.text = it.description

            // Set the toolbar title
            supportActionBar?.title = it.code
        }
    }

    private fun setupFab() {
        binding.fabEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("hod.it@ucc.edu.jm"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Course Inquiry")

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
}