package com.codexdroid.codexdroiddevcv.activities

/**
 * Token Generate for Liquid Swipe android
 *  Token => e20c60c2a22480a757c52b5d0cb37a0c33a2c455
 * */

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.adapters.CustomPagerAdapter
import com.codexdroid.codexdroiddevcv.databinding.ActivityMainBinding
import com.jem.liquidswipe.clippathprovider.LiquidSwipeClipPathProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val liquidSwipeClipPathProviders = Array(5) {
            LiquidSwipeClipPathProvider()
        }

        binding.idLiquidSwipePager.adapter = CustomPagerAdapter(this,liquidSwipeClipPathProviders)

        binding.idLiquidSwipePager.setOnTouchListener { _, event ->
            val waveCenterY = event.y
            liquidSwipeClipPathProviders.map {
                it.waveCenterY = waveCenterY
            }
            false
        }

    }
}



































