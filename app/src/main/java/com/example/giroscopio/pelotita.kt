package com.example.giroscopio

import  android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.AttributeSet
import android.view.View

class pelotita(context: Context, attrs: AttributeSet?) : View(context, attrs), SensorEventListener {
    private val paint = Paint()
    private var x = 0f
    private var y = 0f
    private var vx = 0f
    private var vy = 0f
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    init {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)
    }

    fun start() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(x, y, 50f, paint)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            vx -= event.values[0] / 10f
            vy += event.values[1] / 10f

            x += vx
            y += vy

            if (x < 0 || x > width) {
                vx *= -1
                x = if (x < 0) 0f else width.toFloat()
            }

            if (y < 0 || y > height) {
                vy *= -1
                y = if (y < 0) 0f else height.toFloat()
            }

            invalidate()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
