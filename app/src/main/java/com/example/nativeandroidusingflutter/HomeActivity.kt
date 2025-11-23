package com.example.nativeandroidusingflutter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nativeandroidusingflutter.databinding.ActivityHomeBinding
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

/**
 * HomeActivity - Main dashboard screen
 * Similar to DashboardFlutterActivity in sp-android
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    companion object {
        val TAG: String = HomeActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupCardClickListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.home_title)
        }
    }

    private fun setupCardClickListeners() {
        // Flutter Module Card
        binding.cardFlutter.setOnClickListener {
            openFlutterModule()
        }

        // Profile Card
        binding.cardProfile.setOnClickListener {
            openProfile()
        }

        // Settings Card
        binding.cardSettings.setOnClickListener {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openFlutterModule() {
        // Initialize Flutter Engine
        val flutterEngine = FlutterEngine(this)

        // Start executing Dart code
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine
        FlutterEngineCache.getInstance().put("my_engine_id", flutterEngine)
        FlutterEngineCache.getInstance().put("my_engine_id1", flutterEngine)

        // Launch Flutter Activity with Rive animation
        val intent = MyFlutterActivity.withInitialData(
            this,
            "Welcome from Home Screen!"
        )
        startActivity(intent)
    }

    private fun openProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        // Exit app confirmation
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
        super.onBackPressed()
    }
}
