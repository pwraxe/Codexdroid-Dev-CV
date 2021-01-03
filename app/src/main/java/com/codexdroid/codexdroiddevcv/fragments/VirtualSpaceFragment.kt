package com.codexdroid.codexdroiddevcv.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.FragmentVirtualSpaceBinding
import com.codexdroid.codexdroiddevcv.extra_utility.BounceInterpolator
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import tyrantgit.explosionfield.ExplosionField

class VirtualSpaceFragment : Fragment(),View.OnClickListener,View.OnLongClickListener {

    private lateinit var binding : FragmentVirtualSpaceBinding
    private lateinit var explodeField : ExplosionField

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_virtual_space, container, false)

        explodeField = ExplosionField(context)

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }

        binding.idGitRepoFAB.setOnClickListener(this)
        binding.idPasteBinFAB.setOnClickListener(this)
        binding.idY4wFAB.setOnClickListener(this)
        binding.idPaperFAB.setOnClickListener(this)
        binding.idGistFAB.setOnClickListener(this)
        binding.idImageCloud.setOnClickListener(this)

        binding.idImageCloud.setOnLongClickListener (this)
        binding.idGitRepoFAB.setOnLongClickListener(this)
        binding.idPasteBinFAB.setOnLongClickListener(this)
        binding.idY4wFAB.setOnLongClickListener(this)
        binding.idPaperFAB.setOnLongClickListener(this)
        binding.idGistFAB.setOnLongClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.id_gitRepoFAB -> { openLink("https://github.com/pwraxe") }
            R.id.id_pasteBinFAB -> { openLink("https://pastebin.com/u/akshay258") }
            R.id.id_y4wFAB -> { openLink("https://www.youth4work.com/y/pawarakshay13") }
            R.id.id_paperFAB -> { openLink("https://www.irjet.net/archives/V5/i11/IRJET-V5I11214.pdf") }
            R.id.id_gistFAB -> { openLink("https://gist.github.com/pwraxe") }
            R.id.id_image_cloud -> {
                zoomEffect(v)
                zoomEffect(binding.idGitRepoFAB)
                zoomEffect(binding.idPasteBinFAB)
                zoomEffect(binding.idY4wFAB)
                zoomEffect(binding.idPaperFAB)
                zoomEffect(binding.idGistFAB)
                zoomEffect(binding.view7)
                zoomEffect(binding.view8)
                zoomEffect(binding.view9)
                zoomEffect(binding.view10)
                zoomEffect(binding.view11)
                zoomEffect(binding.view12)
            }
        }
    }

    override fun onLongClick(v: View?): Boolean {
        when(v?.id){
            R.id.id_image_cloud -> {
                blastView(v)
                blastView(binding.idGitRepoFAB)
                blastView(binding.idPasteBinFAB)
                blastView(binding.idY4wFAB)
                blastView(binding.idPaperFAB)
                blastView(binding.idGistFAB)
                blastView(binding.view7)
                blastView(binding.view8)
                blastView(binding.view9)
                blastView(binding.view10)
                blastView(binding.view11)
                blastView(binding.view12)
            }
        }
        return false
    }

    private fun openLink(link : String){
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW,uri)
        startActivity(intent)
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