package com.example.nativeandroidusingflutter

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.nativeandroidusingflutter.databinding.ActivitySplashBinding

/**
 * SplashActivity - Initial loading screen
 * Similar to GettingStartedActivity in sp-android
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    companion object {
        private const val SPLASH_DELAY = 2500L // 2.5 seconds
        val TAG: String = SplashActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if this is the root task (prevent multiple instances)
        if (!isTaskRoot) {
            finish()
            return
        }

        // Initialize app components here (similar to sp-android)
        initializeApp()

        // Navigate to Home after delay
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToHome()
        }, SPLASH_DELAY)
    }

    private fun initializeApp() {
        // Initialize any required components
        // Similar to sp-android's realm initialization, license check, etc.
        // For now, just a placeholder
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        // Disable back button on splash screen
        // Do nothing
    }
}
