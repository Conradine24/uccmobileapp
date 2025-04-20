package com.example.uccitmobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uccitmobileapp.adapter.DirectoryAdapter
import com.example.uccitmobileapp.databinding.ActivityDirectoryBinding
import com.example.uccitmobileapp.db.StaffRepository

class DirectoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDirectoryBinding
    private lateinit var staffRepository: StaffRepository
    private lateinit var directoryAdapter: DirectoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDirectoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        staffRepository = StaffRepository(this)
        setupRecyclerView()
        setupFab()
    }

    private fun setupRecyclerView() {
        val staffList = staffRepository.getAllStaff()
        directoryAdapter = DirectoryAdapter(staffList)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DirectoryActivity)
            adapter = directoryAdapter
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
}