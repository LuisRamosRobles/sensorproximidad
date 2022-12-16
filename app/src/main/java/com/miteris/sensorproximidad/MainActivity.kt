package com.miteris.sensorproximidad

import android.content.ContentValues.TAG
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        val proximitySensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if(proximitySensor == null) {
            Log.e(TAG, "No tiene sensor de proximidad")
            finish()
        }

        //Create listener proximitySensor
        val proximitySensorListener: SensorEventListener = object: SensorEventListener {

            override
            fun onSensorChanged(sensorEvent : SensorEvent) {

                if(sensorEvent.values[0] < proximitySensor.getMaximumRange()){
                    getWindow().decorView.setBackgroundColor(Color.RED)
                    playSoundGato()
                } else {
                    window.decorView.setBackgroundColor(Color.GREEN)
                    stopSound()
                }
            }

            override
            fun onAccuracyChanged(sensor: Sensor, i: Int){}
        }

        // Actualizar en microsegundos
        sensorManager.registerListener(
            proximitySensorListener, proximitySensor, 2 * 1000 * 1000
        )




    }
    //Reproducir música
    //0. - Declaramos lavariable
    var mMediaPlayer: MediaPlayer? = null

    //1. - Creamos la funcion play
    fun playSoundGato() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.gato)
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
    }

    //2. - Pause playback
    fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }

    //3. - Stops playback
    fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    //4. - Destroys the MediaPlayer instance when the app is closed
    override
    fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null

        }
    }

/*
https://www.daypo.com/examen-prog-multimedia-dispositivos-moviles-uf1-ilerna.html

https://www.daypo.com/examen-prog-multimedia-dispositivos-moviles-uf2-ilerna.html

https://www.daypo.com/examen-prog-multimedia-dispositivos-moviles-uf3-ilerna.html

https://developer.android.com/codelabs/basic-android-kotlin-compose-kotlin-fundamentals-practice-problems?hl=es-419#8

https://cursokotlin.com/curso-programacion-kotlin-android/


PRÁCTICA

1.- Ejercicio práctico 1
Las entradas de cine suelen tener un precio diferente según la edad de los espectadores

En el código inicial que se proporciona en el siguiente fragmento de código escribe un programa que calcule los precios de estas entradas basados en la edad

Un precio de entrada infantil de USD 15 para personas de 12 años o menos
Un precio de entrada estándar de 30 USD para personas de entre 13 y 60 años, Los lunes un precio estándar con descuento de USD 25 para el mismo grupo de edad
Un precio para adultos mayores de USD 20 para personas de 61 años o más (asumimos que la edad máxima de un espectador es de 100 años)
Un valor de -1 para indicar que el precio no es válido cuando un usuario ingresa una edad fuera de las especificaciones

fun main() {
   val child = 5
   val adult = 28
   val senior = 87

   val isMonday = true

   println(“The movie ticket price for a person aged $child is
   println(“The movie ticket price for a person aged $adult is
   println(“The movie ticket price for a person aged $senior is
}

fun ticketPrice(age: Int, isMonday: Boolean):Int {
return when(age) |
   in 0…12 -> 15
   in 13…60 -> if(isMonday) 25 else 30
   else -> -1
}




2. Ejercicio práctico 2
Por lo general la pantalla del teléfono se enciende y se apago cuando se presiona el botón de encendido. En cambio, si un teléfono plegable está plegado, su pantalla interna principal no se enciende cuando se presiona el botón de encendido.

En el código inicial que se proporciona en el siguiente fragmento de código, escribe una clase FoldablePhone que se herede de la clase Phone. Debe contener lo siguiente:

Una propiedad que indique si el teléfono está plegado
Un comportamiento de función switchOn() diferente del de la clase Phone para que solo encienda la pantalla cuando el teléfono no esté plegado
Métodos para cambiar el estado de plegado

class Phone(vaar ScreenLightOn Boolean = false){


     */

}