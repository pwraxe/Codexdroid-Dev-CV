package com.codexdroid.codexdroiddevcv.extra_utility

import android.content.Context
import android.os.Vibrator

class VibrationProvider(var context: Context) {

    private val vibrate = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    fun vibrateFor30M(){
        if(vibrate.hasVibrator()) vibrate.vibrate(30)
    }

    fun vibrateFor50M(){
        if(vibrate.hasVibrator()) vibrate.vibrate(50)
    }

    fun vibrateFor70M(){
        if(vibrate.hasVibrator()) vibrate.vibrate(70)
    }

    fun vibrateFor100M(){
        if(vibrate.hasVibrator()) vibrate.vibrate(100)
    }
}




/***
 *
    private fun blastView(view  : View){
        Bloom.with(activity)
            .setParticleRadius(8F)
            .setEffector(
                BloomEffector
                    .Builder()
                    .setDuration(5000L)
                    .setSpeedRange(0.1f,1f)
                    .setFadeOut(1000L)
                    .setAnchor((view.width/2).toFloat(),(view.height/2).toFloat())
                    .build())
            .boom(view)
            explodeField.explode(view)
    }
 *
 *
 *
 *  binding.idKonff.build()
.addColors(Color.RED, Color.GREEN, Color.BLUE)
.setSpeed(-1f)
.addShapes(Shape.Circle, Shape.Square)
.setFadeOutEnabled(true)
.addSizes(Size(8))
.setPosition(0f, width.toFloat(), -100f, 1000f)
.streamFor(50, StreamEmitter.INDEFINITE)
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * **/