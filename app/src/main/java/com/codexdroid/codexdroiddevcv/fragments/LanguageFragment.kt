package com.codexdroid.codexdroiddevcv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.FragmentLanguageBinding
import com.codexdroid.codexdroiddevcv.extra_utility.BounceInterpolator
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import tyrantgit.explosionfield.ExplosionField

class LanguageFragment : Fragment(){


    private lateinit var binding : FragmentLanguageBinding
    private lateinit var explodeField : ExplosionField

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_language, container, false)

        explodeField = ExplosionField(context)

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->

            when(checkedId){
                binding.idMarathiChip.id -> {
                    loadProgressData(360,360,360)
                    binding.idMarathiText.text = "Marathi"
                }
                binding.idHindiChip.id -> {
                    loadProgressData(350,300,330)
                    binding.idMarathiText.text = "Hindi"
                }
                binding.idEnglishChip.id -> {
                    loadProgressData(300,270,250)
                    binding.idMarathiText.text = "English"
                }
            }
        }


        return binding.root
    }

    private fun loadProgressData(read : Int, write : Int, speak : Int){

        binding.idWheel1.setPercentage(read)
        binding.idWheel1.setOnClickListener { zoomEffect(it) }
        binding.idWheel1.setOnLongClickListener {
            blastView(it)
            return@setOnLongClickListener false
        }
        binding.idMarathiText.setOnClickListener { zoomEffect(it) }

        binding.idWheel2.setPercentage(write)
        binding.idWheel2.setOnClickListener { zoomEffect(it) }
        binding.idWheel2.setOnLongClickListener {
            blastView(it)
            return@setOnLongClickListener false
        }

        binding.idWheel3.setPercentage(speak)
        binding.idWheel3.setOnClickListener { zoomEffect(it) }
        binding.idWheel3.setOnLongClickListener {
            blastView(it)
            return@setOnLongClickListener false
        }
    }

    private fun zoomEffect(view: View){
        val anim = AnimationUtils.loadAnimation(context,R.anim.bounce)
        val interpolator = BounceInterpolator(0.2,20.0)
        anim.interpolator = interpolator
        view.startAnimation(anim)
    }

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
}