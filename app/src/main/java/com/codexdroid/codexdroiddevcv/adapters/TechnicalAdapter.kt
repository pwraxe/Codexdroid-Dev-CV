package com.codexdroid.codexdroiddevcv.adapters

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.TechnicalRackBinding
import com.codexdroid.codexdroiddevcv.extra_utility.BounceInterpolator
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import tyrantgit.explosionfield.ExplosionField

class TechnicalAdapter(
    var context: Context,
    var progress : ArrayList<Int>,
    var language : ArrayList<String>,
    var activity: Activity,
    var wheelColor : ArrayList<Int>)
    : RecyclerView.Adapter<TechnicalAdapter.ViewHolder>()  {

    private var explodeField = ExplosionField(context)

    class ViewHolder(var binding : TechnicalRackBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TechnicalRackBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = language.size

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.idWheel.setPercentage(progress[position])
        holder.binding.idLanguageName.text = language[position]
        holder.binding.idWheel.setBackgroundColor(wheelColor[position])
        holder.binding.idWheel.setStepCountText(language[position])

        holder.binding.idLanguageName.setOnClickListener { zoomEffect(it) }
        holder.binding.idWheel.setOnClickListener { zoomEffect(it) }

        holder.binding.idWheel.setOnLongClickListener {
            blastView(it)
            return@setOnLongClickListener false
        }
        holder.binding.idLanguageName.setOnLongClickListener {
            blastView(it)
            return@setOnLongClickListener false
        }
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