package com.codexdroid.codexdroiddevcv.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieDrawable
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.InterestPagerBinding
import com.codexdroid.codexdroiddevcv.extra_utility.BounceInterpolator
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import tyrantgit.explosionfield.ExplosionField

class InterestPagerAdapter(var context : Context,private var activity: Activity)
    : RecyclerView.Adapter<InterestPagerAdapter.ViewHolder>() {

    private var explodeField : ExplosionField = ExplosionField(context)

    private var lottieFiles = arrayOf(
        R.raw.music,
        R.raw.code_failuar,
        R.raw.driving,
        R.raw.games,
        R.raw.travelling,
        R.raw.shopping )

    private var interestText = arrayOf(
        "Music",
        "Coding",
        "Driving",
        "Gaming",
        "Travelling",
        "Shopping")

    private var interestTextDesc = arrayOf(

        context.getString(R.string.interest_music_desc),
        context.getString(R.string.about_desc),
        context.getString(R.string.interest_driving_desc),
        context.getString(R.string.interest_game_desc),
        context.getString(R.string.interest_travel_desc),
        context.getString(R.string.interest_shopping_desc) )



    class ViewHolder(var binding : InterestPagerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(InterestPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = lottieFiles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.idInterestLottie.setAnimation(lottieFiles[position])
        holder.binding.idInterestLottie.repeatCount = LottieDrawable.INFINITE
        holder.binding.idInterestLottie.repeatMode = LottieDrawable.REVERSE
        holder.binding.idInterestLottie.playAnimation()


        holder.binding.idInterestName.text = interestText[position]
        holder.binding.idInterestNameDesc.text = interestTextDesc[position]

        holder.binding.idInterestCard.setOnClickListener { zoomEffect(it) }
        holder.binding.idInterestCard.setOnLongClickListener {
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