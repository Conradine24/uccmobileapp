package com.example.uccitmobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uccitmobileapp.databinding.ActivityAdmissionsBinding

class AdmissionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdmissionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdmissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupButtonClickListeners()
        setupFab()
    }

    private fun setupButtonClickListeners() {
        binding.buttonApply.setOnClickListener {
            // Open UCC application page
            val uri = Uri.parse("https://ucc.edu.jm")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun setupFab() {
        binding.fabEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("hod.it@ucc.edu.jm"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Admissions Inquiry")

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