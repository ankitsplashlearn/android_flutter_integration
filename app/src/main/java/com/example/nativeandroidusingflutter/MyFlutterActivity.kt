package com.example.nativeandroidusingflutter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel

class MyFlutterActivity : FlutterActivity() {
    private val CHANNEL = "com.example.app.communication"
    private var initialData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialData = intent.getStringExtra("initial_data")
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            when (call.method) {
                "sendDataBack" -> {
                    val data = call.argument<String>("data")
                    val resultIntent = Intent().apply {
                        putExtra("result_data", data)
                    }
                    setResult(RESULT_OK, resultIntent)
                    finish()
                    result.success(null)
                }
                "receiveInitialData" -> {
                    result.success(initialData)
                }
                else -> result.notImplemented()
            }
        }

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).invokeMethod("receiveInitialData", initialData)
    }

    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        return FlutterEngineCache.getInstance().get("my_engine_id1")
    }

    companion object {
        fun withInitialData(context: Context, initialData: String): Intent {
            return Intent(context, MyFlutterActivity::class.java).apply {
                putExtra("initial_data", initialData)
            }
        }
    }
}
