package com.finance.checkmysensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testSensor()
    }


    private fun testSensor() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        createSensorListener(Sensor.TYPE_LIGHT)
        createSensorListener(Sensor.TYPE_AMBIENT_TEMPERATURE)
        createSensorListener(Sensor.TYPE_PRESSURE)
        createSensorListener(Sensor.TYPE_PROXIMITY)
        createSensorListener(Sensor.TYPE_RELATIVE_HUMIDITY)
        createSensorListener(Sensor.TYPE_GRAVITY)
    }

    private fun createSensorListener(sensorType: Int) {
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(sensorType),
            SensorManager.SENSOR_DELAY_NORMAL,
            SensorManager.SENSOR_STATUS_ACCURACY_HIGH
        )
    }

    override fun onSensorChanged(event: SensorEvent?) {

        if (event != null) {
            val sensor = event.sensor



            when (sensor.type) {

                Sensor.TYPE_LIGHT -> {
                    checkChanges("isik", event.values, sensor.type)
                }
                Sensor.TYPE_AMBIENT_TEMPERATURE -> {
                    checkChanges("sicaklik", event.values, sensor.type)
                }

                Sensor.TYPE_PRESSURE -> {
                    checkChanges("basinc", event.values, sensor.type)
                }

                Sensor.TYPE_PROXIMITY -> {
                    checkChanges("uzaklik", event.values, sensor.type)
                }
                Sensor.TYPE_RELATIVE_HUMIDITY -> {
                    checkChanges("nem", event.values, sensor.type)
                }
                Sensor.TYPE_GRAVITY -> {
                    checkChanges("yer çekimi", event.values, sensor.type)
                }

            }

        }


    }

    private fun checkChanges(tag: String, newValue: FloatArray, type: Int) {
        for (i in newValue) {
            Log.i(tag, i.toString())
        }


    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.i("accuracy", accuracy.toString())

    }


    val ligtListener = object : SensorEventListener {
        override fun onSensorChanged(p0: SensorEvent?) {
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

        }

    }
}