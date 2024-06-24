package com.example.nativeandroidusingflutter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.example.nativeandroidusingflutter.databinding.ActivityMainBinding
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    companion object {
        const val FLUTTER_REQUEST_CODE = 1001
    }

    var result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK) {
            it.data?.getStringExtra("result_data")?.let { resultData ->
                // Handle the data returned from the Flutter activity
                println("Data from Flutter: $resultData")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.btnOpenFlutter.setOnClickListener {
            // Instantiate a FlutterEngine.
            val flutterEngine = FlutterEngine(this)

            // Start executing Dart code in the background.
            flutterEngine.dartExecutor.executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
            )

            // Cache the FlutterEngine to be used by FlutterActivity.
            FlutterEngineCache.getInstance().put("my_engine_id", flutterEngine)
            FlutterEngineCache.getInstance().put("my_engine_id1", flutterEngine)

            val intent = MyFlutterActivity.withInitialData(this, "Initial data from android side!")
            result.launch(intent)
//            startActivityForResult(intent, FLUTTER_REQUEST_CODE)
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == FLUTTER_REQUEST_CODE && resultCode == RESULT_OK) {
//            data?.getStringExtra("result_data")?.let { resultData ->
//                // Handle the data returned from the Flutter activity
//                println("Data from Flutter: $resultData")
//            }
//        }
//    }
}
