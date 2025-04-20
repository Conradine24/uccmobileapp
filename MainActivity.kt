package com.example.uccitmobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.uccitmobileapp.databinding.ActivityMainBinding
//Conradine Powell 20212755
//Kadeem richardson 20191890
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setSupportActionBar(binding.toolbar)

        setupClickListeners()
        setupFab()
    }

    private fun setupClickListeners() {
        binding.cardDirectory.setOnClickListener {
            startActivity(Intent(this, DirectoryActivity::class.java))
        }

        binding.cardCourses.setOnClickListener {
            startActivity(Intent(this, CoursesActivity::class.java))
        }

        binding.cardAdmissions.setOnClickListener {
            startActivity(Intent(this, AdmissionsActivity::class.java))
        }

        binding.cardSocialMedia.setOnClickListener {
            startActivity(Intent(this, SocialMediaActivity::class.java))
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

            }
        }
    }
}