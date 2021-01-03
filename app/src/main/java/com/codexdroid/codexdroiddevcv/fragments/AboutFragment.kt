package com.codexdroid.codexdroiddevcv.fragments


import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.FragmentAboutBinding
import com.codexdroid.codexdroiddevcv.extra_utility.BounceInterpolator
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import tyrantgit.explosionfield.ExplosionField

class AboutFragment : Fragment(), View.OnClickListener, View.OnLongClickListener{

    private lateinit var binding : FragmentAboutBinding
    private lateinit var explodeField : ExplosionField

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_about, container, false)

        explodeField = ExplosionField(context)

        binding.idAboutCard1.setOnClickListener(this)
        binding.idAboutCard2.setOnClickListener(this)
        binding.idAboutCard3.setOnClickListener(this)
        binding.idAboutCard4.setOnClickListener(this)

        binding.idAboutCard1.setOnLongClickListener(this)
        binding.idAboutCard2.setOnLongClickListener(this)
        binding.idAboutCard3.setOnLongClickListener(this)
        binding.idAboutCard4.setOnLongClickListener(this)

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }
        return binding.root
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

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.id_aboutCard1 -> {zoomEffect(v)}
            R.id.id_aboutCard2 -> {zoomEffect(v)}
            R.id.id_aboutCard3 -> {zoomEffect(v)}
            R.id.id_aboutCard4 -> {zoomEffect(v)}
        }
    }

    override fun onLongClick(v: View?): Boolean {
        when(v?.id){
            R.id.id_aboutCard1 -> { blastView(v) }
            R.id.id_aboutCard2 -> { blastView(v) }
            R.id.id_aboutCard3 -> { blastView(v) }
            R.id.id_aboutCard4 -> { blastView(v) }
        }
        return false
    }
}
