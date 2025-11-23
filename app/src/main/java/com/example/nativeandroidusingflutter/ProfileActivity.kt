package com.example.nativeandroidusingflutter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nativeandroidusingflutter.databinding.ActivityProfileBinding

/**
 * ProfileActivity - User profile screen
 * Similar to profile/settings screens in sp-android
 */
class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    companion object {
        val TAG: String = ProfileActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupProfileData()
        setupActionButtons()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.profile_title)
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupProfileData() {
        // In a real app, you would load this data from a database or API
        binding.apply {
            tvUserName.text = getString(R.string.user_name)
            tvUserEmail.text = getString(R.string.user_email)
            tvPhone.text = getString(R.string.phone_value)
            tvLocation.text = getString(R.string.location_value)
            tvMemberSince.text = getString(R.string.member_since_value)
        }
    }

    private fun setupActionButtons() {
        binding.btnEditProfile.setOnClickListener {
            Toast.makeText(this, "Edit profile clicked", Toast.LENGTH_SHORT).show()
            // In real app: navigate to edit profile screen
        }

        binding.btnLogout.setOnClickListener {
            Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
            // In real app: clear session, navigate to login screen
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
