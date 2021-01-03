package com.codexdroid.codexdroiddevcv.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.`interface`.FragmentsCommunicator
import com.codexdroid.codexdroiddevcv.databinding.ActivityHomeBinding
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import kotlinx.android.synthetic.main.activity_home.*
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import tyrantgit.explosionfield.ExplosionField

class HomeActivity : AppCompatActivity(),FragmentsCommunicator {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var explodeField : ExplosionField

    private lateinit var leftMenu: SlidingRootNav
    private lateinit var rightMenu: SlidingRootNav

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        explodeField = ExplosionField(this)

    }



    private fun blastView(view  : View){
        Bloom.with(this)
            .setParticleRadius(5F)
            .setEffector(BloomEffector.Builder()
                .setDuration(3000L)
                .setFadeOut(1000L)
                .setAnchor((view.width/2).toFloat(),(view.height/2).toFloat())
                .build())
            .boom(view)
        explodeField.explode(view)
    }

    private fun gotoAndCloseLeftMenu(toFrag : Int){
        NavHostFragment.findNavController().navigate(toFrag)
        leftMenu.closeMenu(true)
    }

    private fun gotoAndCloseRightMenu(toFrag: Int){
        NavHostFragment.findNavController().navigate(toFrag)
        rightMenu.closeMenu(true)
    }

    override fun gotoThatFragment(toFragment: Int, which: Int) {
        when(which){
            1 -> gotoAndCloseLeftMenu(toFragment)
            2 -> gotoAndCloseRightMenu(toFragment)
        }
    }

}







/**
 *
 *
 *
leftMenu = SlidingRootNavBuilder(this)
.withDragDistance(150)
.withRootViewScale(0.7f)
.withRootViewElevation(10)
.withRootViewYTranslation(4)
.withMenuLayout(R.layout.menu_myself)
.withContentClickableWhenMenuOpened(true)
.withGravity(SlideGravity.LEFT)
.addDragListener {
// 0.0 => menu close
// 1.0 => menu open
leftMenu.layout.findViewById<ConstraintLayout>(R.id.id_rightSideMenu).visibility = View.GONE
leftMenu.layout.findViewById<ConstraintLayout>(R.id.id_leftSideMenu).visibility = View.VISIBLE

}
.inject()

if (leftMenu.isMenuOpened) { leftMenu.isMenuLocked = true }



leftMenu.layout.findViewById<TextView>(R.id.id_interestText).setOnClickListener {
try{
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_interestFragment)
leftMenu.closeMenu(true)
}catch (ex : IllegalArgumentException){
onBackPressed()
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_interestFragment)
leftMenu.closeMenu(true)
}
}

leftMenu.layout.findViewById<TextView>(R.id.id_languageText).setOnClickListener {
try {
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_languageFragment)
leftMenu.closeMenu(true)
} catch (ex: IllegalArgumentException) {
onBackPressed()
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_languageFragment)
leftMenu.closeMenu(true)
}
}

leftMenu.layout.findViewById<TextView>(R.id.id_aboutText).setOnClickListener {
try {
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
leftMenu.closeMenu(true)
} catch (ex: IllegalArgumentException) {
onBackPressed()
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
leftMenu.closeMenu(true)
}
}
leftMenu.layout.findViewById<TextView>(R.id.id_contactText).setOnClickListener {
try {
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_contactFragment)
leftMenu.closeMenu(true)
} catch (ex: IllegalArgumentException) {
onBackPressed()
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_contactFragment)
leftMenu.closeMenu(true)
}
}

leftMenu.layout.findViewById<TextView>(R.id.id_socialMediaText).setOnClickListener {
try {
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_socialMediaFragment)
leftMenu.closeMenu(true)
} catch (ex: IllegalArgumentException) {
onBackPressed()
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_socialMediaFragment)
leftMenu.closeMenu(true)
}
}
leftMenu.layout.findViewById<TextView>(R.id.id_copyrightText).setOnClickListener {
try {
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_copyrightFragment)
leftMenu.closeMenu(true)
} catch (ex: IllegalArgumentException) {
onBackPressed()
NavHostFragment.findNavController().navigate(R.id.action_homeFragment_to_copyrightFragment)
leftMenu.closeMenu(true)
}
}

leftMenu.layout.findViewById<TextView>(R.id.id_interestText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
leftMenu.layout.findViewById<TextView>(R.id.id_languageText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
leftMenu.layout.findViewById<TextView>(R.id.id_aboutText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
leftMenu.layout.findViewById<TextView>(R.id.id_contactText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
leftMenu.layout.findViewById<TextView>(R.id.id_socialMediaText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
leftMenu.layout.findViewById<TextView>(R.id.id_copyrightText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }

rightMenu = SlidingRootNavBuilder(activity)
.withDragDistance(130)
.withRootViewScale(0.7f)
.withRootViewElevation(10)
.withRootViewYTranslation(4)
.withMenuLayout(R.layout.menu_myself)
.withContentClickableWhenMenuOpened(true)
.withGravity(SlideGravity.RIGHT)
.addDragListener {
rightMenu.layout.findViewById<ConstraintLayout>(R.id.id_leftSideMenu).visibility = View.GONE
rightMenu.layout.findViewById<ConstraintLayout>(R.id.id_rightSideMenu).visibility = View.VISIBLE

}
.inject()

if (rightMenu.isMenuOpened){

rightMenu.isMenuLocked = true
rightMenu.layout.findViewById<ConstraintLayout>(R.id.id_leftSideMenu).visibility = View.GONE
}

rightMenu.layout.findViewById<TextView>(R.id.id_educationText).setOnClickListener { gotoAndCloseRightMenu(R.id.action_homeFragment_to_educationFragment) }
rightMenu.layout.findViewById<TextView>(R.id.id_tech_skillText).setOnClickListener { gotoAndCloseRightMenu(R.id.action_homeFragment_to_technicalSkillFragment) }
rightMenu.layout.findViewById<TextView>(R.id.id_virtual_spaceText).setOnClickListener { gotoAndCloseRightMenu(R.id.action_homeFragment_to_virtualSpaceFragment) }
rightMenu.layout.findViewById<TextView>(R.id.id_certificateText).setOnClickListener{ gotoAndCloseRightMenu(R.id.action_homeFragment_to_certificatesFragment) }
rightMenu.layout.findViewById<TextView>(R.id.id_projectText).setOnClickListener { gotoAndCloseRightMenu(R.id.action_homeFragment_to_projectsFragment) }
rightMenu.layout.findViewById<TextView>(R.id.id_experienceText).setOnClickListener { gotoAndCloseRightMenu(R.id.action_homeFragment_to_experienceFragment) }

rightMenu.layout.findViewById<TextView>(R.id.id_educationText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
rightMenu.layout.findViewById<TextView>(R.id.id_tech_skillText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
rightMenu.layout.findViewById<TextView>(R.id.id_virtual_spaceText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
rightMenu.layout.findViewById<TextView>(R.id.id_certificateText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
rightMenu.layout.findViewById<TextView>(R.id.id_projectText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
rightMenu.layout.findViewById<TextView>(R.id.id_experienceText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
 */