package com.example.tossingapp

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastShakeTime: Long = 0

    private lateinit var mediaPlayer: MediaPlayer
    private var cheatModeActive = false // Variable to track cheat mode
    private var isShaking = false // To prevent multiple triggers from a single shake

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupListeners()

        // Initialize SensorManager and Accelerometer
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // Start the background color transition effect
        startBackgroundTransition()
    }

    private fun setupListeners() {
        val ivCoin = findViewById<ImageView>(R.id.Ivcoin)
        val btnFlip = findViewById<Button>(R.id.button)

        // Coin ImageView onClickListener
        ivCoin.setOnClickListener {
            flipCoin()
        }

        // Button onClickListener
        btnFlip.setOnClickListener {
            // Disable cheat mode when the button is clicked
            cheatModeActive = false
            flipCoin()
        }
    }

    private fun flipCoin() {
        // Play sound effect
        playSound()

        val ivCoin = findViewById<ImageView>(R.id.Ivcoin)
        val result = if (cheatModeActive) {
            1 // Cheat Mode: Always heads
        } else {
            (1..2).random() // Regular random flip
        }

        // Trigger flip animation and show result
        if (result == 1) {
            flipAnimation(R.drawable.heads, "Heads")
        } else {
            flipAnimation(R.drawable.tails, "Tails")
        }
    }

    private fun flipAnimation(resultImage: Int, resultText: String) {
        val imageView = findViewById<ImageView>(R.id.Ivcoin)
        imageView.animate().apply {
            duration = 1000
            rotationYBy(1800f) // Adding rotation for flip effect
            imageView.isClickable = false
            alpha(0f) // Fade out the coin during flip animation
        }.withEndAction {
            imageView.setImageResource(resultImage)
            imageView.alpha = 1f // Fade back in after animation ends
            Toast.makeText(this, resultText, Toast.LENGTH_SHORT).show()
            imageView.isClickable = true
        }
    }

    private fun playSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.coin_flip_sound)
        mediaPlayer.start()

        // Release the MediaPlayer once the sound is done
        mediaPlayer.setOnCompletionListener {
            it.release()
        }
    }

    // Shake Detection
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble()) - SensorManager.GRAVITY_EARTH
            if (acceleration > 1.8 && !isShaking) { // Reduced shake threshold to 1.8
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastShakeTime > 1000) { // Prevent multiple triggers
                    lastShakeTime = currentTime
                    isShaking = true

                    // Toggle Cheat Mode on Shake
                    cheatModeActive = !cheatModeActive

                    // Trigger coin flip and vibration
                    flipCoin()
                    vibrate()

                    // Notify user about cheat mode status
                    val message = if (cheatModeActive) {
                        "Cheat Mode Activated"
                    } else {
                        "Cheat Mode Deactivated"
                    }
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            val vibrationEffect = VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(vibrationEffect)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No need to handle this
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    // Start the background color transition
    private fun startBackgroundTransition() {
        // Get reference to your ConstraintLayout
        val constraintLayout = findViewById<ConstraintLayout>(R.id.main)

        // Define multiple colors for the background transition
        val colors = intArrayOf(
            Color.parseColor("#FFFFFF"), // White
            Color.parseColor("#4CAF50"), // Green
            Color.parseColor("#2196F3"), // Blue
            Color.parseColor("#FFEB3B")  // Yellow
        )

        // Create a ValueAnimator for smooth background color transition between multiple colors
        val colorAnimation = ValueAnimator.ofObject(android.animation.ArgbEvaluator(), colors[0], colors[1], colors[2], colors[3])
        colorAnimation.duration = 30000 // Duration of each color transition (in milliseconds)
        colorAnimation.repeatCount = ValueAnimator.INFINITE // Repeat indefinitely

        // Update the background color during the animation
        colorAnimation.addUpdateListener { animator ->
            constraintLayout.setBackgroundColor(animator.animatedValue as Int)
        }

        // Start the animation
        colorAnimation.start()
    }
}
