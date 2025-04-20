package com.example.uccitmobileapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uccitmobileapp.adapter.SocialMediaPagerAdapter
import com.example.uccitmobileapp.databinding.ActivitySocialMediaBinding
import com.google.android.material.tabs.TabLayoutMediator

class SocialMediaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySocialMediaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocialMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViewPager()
        setupFab()
    }

    private fun setupViewPager() {
        val socialMediaPagerAdapter = SocialMediaPagerAdapter(this)
        binding.viewPager.adapter = socialMediaPagerAdapter

        // Connect the TabLayout with ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Facebook"
                1 -> "Twitter"
                2 -> "Instagram"
                else -> ""
            }
        }.attach()
    }

    private fun setupFab() {
        binding.fabEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("hod.it@ucc.edu.jm"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Social Media Inquiry")

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