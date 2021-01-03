package com.codexdroid.codexdroiddevcv.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.FragmentSocialMediaBinding
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import com.yarolegovich.slidingrootnav.SlidingRootNav
import tyrantgit.explosionfield.ExplosionField
import java.net.URLEncoder


class SocialMediaFragment : Fragment() {

    private lateinit var binding : FragmentSocialMediaBinding
    private lateinit var explodeField : ExplosionField

    private val message = "Hello Akshay, I just viewed your CV :)"
    private var socialLinks = arrayOf(
        "https://github.com/pwraxe",
        "https://www.instagram.com/akshayxpawar",
        "https://www.linkedin.com/in/akshay-pawar-b7a0a8135/",
        1,   // whats app
        2,   // email
        "https://www.facebook.com/akshay.pawar.777701",
        "https://twitter.com/pawarakshay247",
        "https://t.me/Akshay1096"    //telegram
    )
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_social_media, container, false)
        explodeField = ExplosionField(context)

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }

        binding.idCircularMenu.setMainMenu(
            Color.parseColor("#FFFFFF"), R.drawable.ic_menu,R.drawable.ic_close)
                .addSubMenu(Color.parseColor("#FFFFFF"),resources.getDrawable(R.drawable.ic_github,null))
                .addSubMenu(Color.parseColor("#FFFFFF"), resources.getDrawable(R.drawable.ic_instagram,null))
                .addSubMenu(Color.parseColor("#FFFFFF"), resources.getDrawable(R.drawable.ic_linkedin,null))
                .addSubMenu(Color.parseColor("#FFFFFF"), resources.getDrawable(R.drawable.ic_whatsapp,null))
                .addSubMenu(Color.parseColor("#FFFFFF"), resources.getDrawable(R.drawable.ic_email,null))
                .addSubMenu(Color.parseColor("#FFFFFF"), resources.getDrawable(R.drawable.ic_facebook,null))
                .addSubMenu(Color.parseColor("#FFFFFF"), resources.getDrawable(R.drawable.ic_twitter,null))
                .addSubMenu(Color.parseColor("#FFFFFF"), resources.getDrawable(R.drawable.ic_telegram,null))
                .setOnMenuSelectedListener{position ->

                    when(position){
                        0 -> { openLink(position) }
                        1 -> { openLink(position) }
                        2 -> { openLink(position) }
                        3 -> { openWhatsApp() }
                        4 -> { openEmail() }
                        5 -> { openLink(position) }
                        6 -> { openLink(position) }
                        7 -> { openLink(position) }
                    }
                }
        return binding.root
    }


    private fun openLink(position : Int){
        val uri = Uri.parse(socialLinks[position].toString())
        val intent = Intent(Intent.ACTION_VIEW,uri)
        startActivity(intent)
    }

    private fun openWhatsApp(){

        val packageManager = requireContext().packageManager
        val i = Intent(Intent.ACTION_VIEW)
        try {
            val url = "https://api.whatsapp.com/send?phone=" + "+919657513437" + "&text=" + URLEncoder.encode(message, "UTF-8")
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)
            if (i.resolveActivity(packageManager) != null) {
                context?.startActivity(i)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "You did not installed WhatsApp, Please install it and then share message", Toast.LENGTH_LONG).show()
        }
    }

    private fun openEmail(){

        //Uri.parse("mailto:developer.codexdroid@gmail.com")
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("developer.codexdroid@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT,"Viewed your CV")
        intent.putExtra(Intent.EXTRA_TEXT,message)
        intent.type = "message/rfc822"
        intent.setPackage("com.google.android.gm")
        startActivity(Intent.createChooser(intent,"Send with : "))
    }

    private fun openTelegram(){


        val packageManager = requireContext().packageManager
        val intent = Intent(Intent.ACTION_VIEW)
        try {
            val url = "https://api.whatsapp.com/send?phone=" + "+919657513437" + "&text=" + URLEncoder.encode(message, "UTF-8")
            intent.setPackage("com.whatsapp")
            intent.data = Uri.parse(url)
            if (intent.resolveActivity(packageManager) != null) {
                context?.startActivity(intent)
            }
        }catch (exe : Exception){
            Toast.makeText(context, "You did not installed Telegram, Please install it and then share message", Toast.LENGTH_LONG).show()
        }



        if(isAppInstalledOrNot("org.telegram.messenger")){

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.setPackage("org.telegram.messenger")
            intent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(intent)

        }else{
            Toast.makeText(context, "You did not installed Telegram, Please install it and then share message", Toast.LENGTH_LONG).show()
        }
    }

    private fun isAppInstalledOrNot(packName : String) : Boolean{
        val pm = activity?.packageManager
        return try {
            pm?.getPackageInfo(packName,PackageManager.GET_ACTIVITIES)
            true
        }catch (ex : PackageManager.NameNotFoundException){
            false
        }

    }

}