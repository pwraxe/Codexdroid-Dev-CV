package com.codexdroid.codexdroiddevcv.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.CertificatePagerBinding
import com.codexdroid.codexdroiddevcv.extra_utility.BounceInterpolator
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import tyrantgit.explosionfield.ExplosionField

class CertificateAdapter (var context : Context, private var activity: Activity) : RecyclerView.Adapter<CertificateAdapter.ViewHolder>()  {


    private var explodeField = ExplosionField(context)

    private var certificates = arrayOf(

        ContextCompat.getDrawable(context,R.drawable.certificate_internship_thinkershub),
        ContextCompat.getDrawable(context,R.drawable.certificate_c_iit_bombay),
        ContextCompat.getDrawable(context,R.drawable.certificate_cpp_iit_bombay),
        ContextCompat.getDrawable(context,R.drawable.certificate_java_iit_bombay),

        ContextCompat.getDrawable(context,R.drawable.certificate_unanth_java),
        ContextCompat.getDrawable(context,R.drawable.certificate_unanth2_java),
        ContextCompat.getDrawable(context,R.drawable.certificate_unanth_php),
        ContextCompat.getDrawable(context,R.drawable.certificate_unanth_mysql),

        ContextCompat.getDrawable(context,R.drawable.certificate_php_udemy),
        ContextCompat.getDrawable(context,R.drawable.certificate_capgemini),
        ContextCompat.getDrawable(context,R.drawable.certificate_cisco))

    private var certificateNames = arrayOf(
        "For Android Internship",
        "For Learn C",
        "For Learn C++",
        "For Learn Java",
        "For Self Learn Java",
        "For Self Learn Java",
        "For Self Learn PHP",
        "For Self Learn MySQL",
        "For Self Learn PHP",
        "Capgemini Challenge by TechGig ",
        "Workshop on Networking")


    private var certificateDesc = arrayOf(

        "Done Android Internship at ThinkersHub Org.",
        "C Exam held by IIT Bombay",
        "C++ Exam held by IIT Bombay",
        "JAVA Exam held by IIT Bombay",
        "With Self Study, Learned Java by Unanth Free Course, Online",
        "With Self Study, Learned Java by Unanth Free Course, Online",
        "With Self Study, Learned PHP by Unanth Free Course, Online",
        "With Self Study, Learned MySQL by Unanth Free Course, Online",
        "With Self Study, Learned PHP by Unanth Free Course, Online",
        "Participated in Capgemini TECH Challenge by TechGig",
        "Cisco Networking Workshop held in Collage")

    private var count = arrayOf("1","2","3","4","5","6","7","8","9","10","11")

    class ViewHolder(var binding : CertificatePagerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(CertificatePagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = certificates.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.idCertificateImage.setImageDrawable(certificates[position])
        holder.binding.idCertificateName.text = certificateNames[position]
        holder.binding.idCertificateDesc.text = certificateDesc[position]
        holder.binding.idCertPageCount.text = "${count[position]}/11"

        holder.binding.idCartCard.setOnClickListener { zoomEffect(it) }
        holder.binding.idCartCard.setOnLongClickListener {
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