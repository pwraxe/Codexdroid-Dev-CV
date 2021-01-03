package com.codexdroid.codexdroiddevcv.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.EducationRackBinding
import com.codexdroid.codexdroiddevcv.extra_utility.BounceInterpolator
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import tyrantgit.explosionfield.ExplosionField

class EducationAdapter(var context: Context,var activity: Activity) : RecyclerView.Adapter<EducationAdapter.ViewHolder>(){

    private var explodeField : ExplosionField = ExplosionField(context)

    private var std = arrayOf(
        "1st to 4th Standard",
        "5th to SSC (10th)",
        "HSC (12th Science)",
        "Bachelor Degree (Information Technology)",null)

    private var collageName = arrayOf(
        "Abhinav Bal Vikas Mandir, Sinnar",
        "L.S.B. Waje Vidyalay Sinnar",
        "G.M.D.Arts,B.W.Commerce and Science College, Sinnar",
        "Sir Visvesvaraya Institute Of Technology, Nashik",null)

    private var year = arrayOf("2003","2006","2012","2014","2019","till Now")

    private var percentage = arrayOf("70 % (in 4th)","69.82 % (in SSC)","56.31 %","68.14 %",null)

    class ViewHolder(var binding : EducationRackBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EducationRackBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = std.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.idEduName.text = collageName[position]
        holder.binding.idFromToName.text = std[position]
        holder.binding.idEduPerc.text = percentage[position]
        holder.binding.idFromYear.text = year[position]
        holder.binding.idToYear.text = year[position+1]

        if (std[position] == null){
            holder.binding.idEducationCard.visibility = View.GONE
            holder.binding.idLottieLookingForJob.visibility = View.VISIBLE
        }


        holder.binding.idEducationCard.setOnClickListener { zoomEffect(it) }
        holder.binding.idEducationCard.setOnLongClickListener { blastView(it)
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