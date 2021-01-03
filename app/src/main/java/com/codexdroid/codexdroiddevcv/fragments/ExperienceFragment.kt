package com.codexdroid.codexdroiddevcv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.FragmentExperienceBinding
import com.codexdroid.codexdroiddevcv.extra_utility.BounceInterpolator
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import tyrantgit.explosionfield.ExplosionField

class ExperienceFragment : Fragment(){

    private lateinit var binding : FragmentExperienceBinding
    private lateinit var explodeField : ExplosionField

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_experience, container, false)
        explodeField = ExplosionField(context)

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }

        binding.idExperienceCard.setOnClickListener { zoomEffect(it) }
        binding.idExperienceCard.setOnLongClickListener {
            blastView(it)
            return@setOnLongClickListener false
        }
        binding.idExperienceCard2.setOnClickListener { zoomEffect(it) }
        binding.idExperienceCard2.setOnLongClickListener {
            blastView(it)
            return@setOnLongClickListener false
        }

        return binding.root
    }

    private fun zoomEffect(view: View){
        val anim = AnimationUtils.loadAnimation(context, R.anim.bounce)
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