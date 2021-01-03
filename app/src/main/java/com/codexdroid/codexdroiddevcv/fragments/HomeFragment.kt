package com.codexdroid.codexdroiddevcv.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.`interface`.FragmentsCommunicator
import com.codexdroid.codexdroiddevcv.databinding.FragmentHomeBinding
import com.codexdroid.codexdroiddevcv.extra_utility.BounceInterpolator
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import nl.dionsegijn.konfetti.emitters.StreamEmitter
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import tyrantgit.explosionfield.ExplosionField
import kotlin.IllegalArgumentException

class HomeFragment : Fragment(), View.OnClickListener, View.OnLongClickListener {

    private lateinit var binding: FragmentHomeBinding
    private var width = 0
    private var height = 0
    private lateinit var explodeField: ExplosionField

    private lateinit var communicator: FragmentsCommunicator


    private val message = "Hello Akshay, I just viewed your CV :)"

    private lateinit var bottomSheet: BottomSheetBehavior<ConstraintLayout>

    private lateinit var leftMenu: SlidingRootNav
    private lateinit var rightMenu: SlidingRootNav


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        communicator = activity as FragmentsCommunicator

        explodeField = ExplosionField(context)

        width = activity?.windowManager?.defaultDisplay?.width!!
        height = activity?.windowManager?.defaultDisplay?.height!!
        binding.idGlowAnim.startAnimation()

        bottomSheet = BottomSheetBehavior.from(binding.idConstrainBottomSheet)

        binding.idProfilePic.setOnClickListener(this)
        binding.idMyCircularPic.setOnClickListener(this)
        binding.idDevEmail.setOnClickListener(this)
        binding.idMagicLottie.setOnClickListener(this)

        particleAnimation()
        explodeElement()

        binding.idConstrainBottomSheet.setOnLongClickListener(this)
        binding.myName.setOnLongClickListener(this)
        binding.idMyPosition.setOnLongClickListener(this)
        binding.idMyDesc.setOnLongClickListener(this)
        binding.myBirthDate.setOnLongClickListener(this)
        binding.idMyCircularPic.setOnLongClickListener(this)



        initializedLeftRightMenu()

        return binding.root
    }

    private fun initializedLeftRightMenu() {

        leftMenu = SlidingRootNavBuilder(activity)
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
                findNavController(this).navigate(R.id.action_homeFragment_to_interestFragment)
                leftMenu.closeMenu(true)
            }catch (ex : IllegalArgumentException){
                findNavController().popBackStack()
                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_interestFragment)
                    leftMenu.closeMenu(true)
                }catch (ex1 : IllegalArgumentException){
                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_interestFragment)
                        leftMenu.closeMenu()
                    }catch (ex2 : IllegalArgumentException){
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_interestFragment)
                        leftMenu.closeMenu()
                    }
                }
                leftMenu.closeMenu(true)
            }
        }

        leftMenu.layout.findViewById<TextView>(R.id.id_languageText).setOnClickListener {
            try {
                findNavController(this).navigate(R.id.action_homeFragment_to_languageFragment)
                leftMenu.closeMenu(true)
            } catch (ex: IllegalArgumentException) {
                findNavController().popBackStack()

                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_languageFragment)
                    leftMenu.closeMenu(true)

                }catch (ex1 : IllegalArgumentException){

                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_languageFragment)
                        leftMenu.closeMenu()
                    }catch (ex2 : IllegalArgumentException){
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_languageFragment)
                        leftMenu.closeMenu()
                    }
                }
                leftMenu.closeMenu(true)
            }
        }

        leftMenu.layout.findViewById<TextView>(R.id.id_aboutText).setOnClickListener {
            try {
                findNavController(this).navigate(R.id.action_homeFragment_to_aboutFragment)
                leftMenu.closeMenu(true)
            } catch (ex: IllegalArgumentException) {

                findNavController().popBackStack()

                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_aboutFragment)
                    leftMenu.closeMenu(true)
                }catch (ex1 : IllegalArgumentException){

                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_aboutFragment)
                        leftMenu.closeMenu()
                    }catch (ex2 : IllegalArgumentException) {
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_aboutFragment)
                        leftMenu.closeMenu()
                    }
                }
                leftMenu.closeMenu(true)
            }
        }

        leftMenu.layout.findViewById<TextView>(R.id.id_contactText).setOnClickListener {
            try {
                findNavController(this).navigate(R.id.action_homeFragment_to_contactFragment)
                leftMenu.closeMenu(true)
            } catch (ex: IllegalArgumentException) {

                findNavController().popBackStack()
                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_contactFragment)
                    leftMenu.closeMenu(true)
                }catch (ex1 : IllegalArgumentException){
                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_contactFragment)
                        leftMenu.closeMenu()
                    }catch (ex2 : IllegalArgumentException) {
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_contactFragment)
                        leftMenu.closeMenu()
                    }

                }
                leftMenu.closeMenu(true)

            }
        }

        leftMenu.layout.findViewById<TextView>(R.id.id_socialMediaText).setOnClickListener {
            try {
                findNavController(this).navigate(R.id.action_homeFragment_to_socialMediaFragment)
                leftMenu.closeMenu(true)
            } catch (ex: IllegalArgumentException) {
                findNavController().popBackStack()
                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_socialMediaFragment)
                    leftMenu.closeMenu(true)

                }catch (ex1 : IllegalArgumentException){

                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_socialMediaFragment)
                        leftMenu.closeMenu()
                    }catch (ex2 : IllegalArgumentException) {
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_socialMediaFragment)
                        leftMenu.closeMenu()
                    }
                }
                leftMenu.closeMenu(true)

            }
        }

        leftMenu.layout.findViewById<TextView>(R.id.id_copyrightText).setOnClickListener {
            try {
                findNavController(this).navigate(R.id.action_homeFragment_to_copyrightFragment)
                leftMenu.closeMenu(true)
            } catch (ex: IllegalArgumentException) {

                findNavController().popBackStack()
                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_copyrightFragment)
                    leftMenu.closeMenu(true)
                }catch (ex1 : IllegalArgumentException){

                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_copyrightFragment)
                        leftMenu.closeMenu()
                    }catch (ex2 : IllegalArgumentException) {
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_copyrightFragment)
                        leftMenu.closeMenu()
                    }
                }
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

        if (rightMenu.isMenuOpened){ rightMenu.isMenuLocked = true }

        rightMenu.layout.findViewById<TextView>(R.id.id_educationText).setOnClickListener {

            try {
                findNavController(this).navigate(R.id.action_homeFragment_to_educationFragment)
                rightMenu.closeMenu(true)
            } catch (ex: IllegalArgumentException) {
                findNavController().popBackStack()
                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_educationFragment)
                    rightMenu.closeMenu(true)
                }catch (ex1 : IllegalArgumentException){

                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_educationFragment)
                        rightMenu.closeMenu(true)
                    }catch (ex2 : IllegalArgumentException) {
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_educationFragment)
                        rightMenu.closeMenu(true)
                    }
                }
                rightMenu.closeMenu(true)
            }
        }
        rightMenu.layout.findViewById<TextView>(R.id.id_tech_skillText).setOnClickListener {
            try {
                findNavController(this).navigate(R.id.action_homeFragment_to_technicalSkillFragment)
                rightMenu.closeMenu(true)
            } catch (ex: IllegalArgumentException) {

                findNavController().popBackStack()
                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_technicalSkillFragment)
                    rightMenu.closeMenu(true)
                }catch (ex1 : IllegalArgumentException){

                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_technicalSkillFragment)
                        rightMenu.closeMenu(true)
                    }catch (ex2 : IllegalArgumentException) {
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_technicalSkillFragment)
                        rightMenu.closeMenu(true)
                    }
                }
                rightMenu.closeMenu(true)
            }
        }

        rightMenu.layout.findViewById<TextView>(R.id.id_virtual_spaceText).setOnClickListener {

            try {
                findNavController(this).navigate(R.id.action_homeFragment_to_virtualSpaceFragment)
                rightMenu.closeMenu(true)
            } catch (ex: IllegalArgumentException) {
                findNavController().popBackStack()
                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_virtualSpaceFragment)
                    rightMenu.closeMenu(true)
                }catch (ex1 : IllegalArgumentException){

                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_virtualSpaceFragment)
                        rightMenu.closeMenu(true)
                    }catch (ex2 : IllegalArgumentException) {
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_virtualSpaceFragment)
                        rightMenu.closeMenu(true)
                    }
                }
                rightMenu.closeMenu(true)
            }
        }
        rightMenu.layout.findViewById<TextView>(R.id.id_certificateText).setOnClickListener{
            try {
                findNavController(this).navigate(R.id.action_homeFragment_to_certificatesFragment)
                rightMenu.closeMenu(true)
            } catch (ex: IllegalArgumentException) {

                findNavController().popBackStack()
                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_certificatesFragment)
                    rightMenu.closeMenu(true)
                }catch (ex1 : IllegalArgumentException){

                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_certificatesFragment)
                        rightMenu.closeMenu(true)
                    }catch (ex2 : IllegalArgumentException) {
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_certificatesFragment)
                        rightMenu.closeMenu(true)
                    }
                }
                rightMenu.closeMenu(true)

            }
        }
        rightMenu.layout.findViewById<TextView>(R.id.id_projectText).setOnClickListener {
            try {
                findNavController(this).navigate(R.id.action_homeFragment_to_projectsFragment)
                rightMenu.closeMenu(true)
            } catch (ex: IllegalArgumentException) {

                findNavController().popBackStack()
                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_projectsFragment)
                    rightMenu.closeMenu(true)
                }catch (ex1 : IllegalArgumentException){

                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_projectsFragment)
                        rightMenu.closeMenu(true)
                    }catch (ex2 : IllegalArgumentException) {
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_projectsFragment)
                        rightMenu.closeMenu(true)
                    }
                }
                rightMenu.closeMenu(true)
            }
        }
        rightMenu.layout.findViewById<TextView>(R.id.id_experienceText).setOnClickListener {
            try {
                findNavController(this).navigate(R.id.action_homeFragment_to_experienceFragment)
                rightMenu.closeMenu(true)
            } catch (ex: IllegalArgumentException) {

                findNavController().popBackStack()
                try{
                    findNavController(this).navigate(R.id.action_homeFragment_to_experienceFragment)
                    rightMenu.closeMenu(true)

                }catch (ex1 : IllegalArgumentException){

                    try{
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_experienceFragment)
                        rightMenu.closeMenu(true)
                    }catch (ex2 : IllegalArgumentException) {
                        findNavController().popBackStack()
                        findNavController(this).navigate(R.id.action_homeFragment_to_experienceFragment)
                        rightMenu.closeMenu(true)
                    }
                }
                rightMenu.closeMenu(true)
            }
        }


        rightMenu.layout.findViewById<TextView>(R.id.id_educationText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
        rightMenu.layout.findViewById<TextView>(R.id.id_tech_skillText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
        rightMenu.layout.findViewById<TextView>(R.id.id_virtual_spaceText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
        rightMenu.layout.findViewById<TextView>(R.id.id_certificateText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
        rightMenu.layout.findViewById<TextView>(R.id.id_projectText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }
        rightMenu.layout.findViewById<TextView>(R.id.id_experienceText).setOnLongClickListener { blastView(it); return@setOnLongClickListener false }

    }


    private fun explodeElement() {

        binding.idProfilePic.setOnLongClickListener {
            blastView(it)
            binding.idLottieError404.visibility = View.VISIBLE
            return@setOnLongClickListener false
        }

        binding.idMyName.setOnLongClickListener {
            blastView(it)
            return@setOnLongClickListener false
        }

        binding.idMyPosition.setOnLongClickListener {
            blastView(it)
            return@setOnLongClickListener false
        }

        binding.idMyDesc.setOnLongClickListener {
            blastView(it)
            return@setOnLongClickListener false
        }
    }

    private fun blastView(view: View) {

        Bloom.with(activity)
            .setParticleRadius(8F)
            .setEffector(
                BloomEffector
                    .Builder()
                    .setDuration(5000L)
                    .setSpeedRange(0.1f, 1f)
                    .setFadeOut(1000L)
                    .setAnchor((view.width / 2).toFloat(), (view.height / 2).toFloat())
                    .build()
            )
            .boom(view)

        explodeField.explode(view)
    }



    private fun particleAnimation() {
        binding.idKonfettiView.build()
            .addColors(Color.RED, Color.GREEN, Color.BLUE)
            .setSpeed(-1f)
            .addShapes(Shape.Circle, Shape.Square)
            .setFadeOutEnabled(true)
            .addSizes(Size(8))
            .setPosition(0f, width.toFloat(), -100f, 1000f)
            .streamFor(100, StreamEmitter.INDEFINITE)


        binding.idKonfettiView.setOnClickListener {

            binding.idKonfettiView.build()
                .setPosition(0f, width.toFloat(), 0f, height.toFloat())
                .addShapes(
                    Shape.DrawableShape(
                        resources.getDrawable(
                            R.drawable.ic_codexdroid_logo_svg,
                            null
                        )
                    )
                )
                .addSizes(Size(24))
                .addColors(Color.WHITE)
                .burst(100)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.id_profilePic -> {
                bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            }
            R.id.id_myCircularPic -> {
                val anim = AnimationUtils.loadAnimation(context, R.anim.bounce)
                val interpolator = BounceInterpolator(0.2, 20.0)
                anim.interpolator = interpolator
                view.startAnimation(anim)
            }
            R.id.id_dev_email -> { openEmail() }

            R.id.id_magicLottie -> {
                bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                Toast.makeText(context, "There is Something hidden in this app, \n try to find out and know more.", Toast.LENGTH_LONG).show()
            }
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


    override fun onLongClick(v: View?): Boolean {
        when (v?.id) {
            R.id.id_constrain_bottomSheet -> {
                blastView(v)
            }
            R.id.id_myName -> {
                blastView(v)
            }
            R.id.id_myPosition -> {
                blastView(v)
            }
            R.id.id_myDesc -> {
                blastView(v)
            }
            R.id.my_birth_date -> {
                blastView(v)
            }
            R.id.id_myCircularPic -> {
                blastView(v)
            }
        }
        return false
    }

}