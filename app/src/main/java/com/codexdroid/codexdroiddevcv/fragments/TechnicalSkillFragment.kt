package com.codexdroid.codexdroiddevcv.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.adapters.TechnicalAdapter
import com.codexdroid.codexdroiddevcv.databinding.FragmentTechnicalSkillBinding
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import tyrantgit.explosionfield.ExplosionField

class TechnicalSkillFragment : Fragment() {

    private lateinit var binding : FragmentTechnicalSkillBinding
    private lateinit var explodeField : ExplosionField



    private var language = arrayListOf("C","C++","Core Java","Kotlin","HTML","CSS","PHP","MySQL","XML","OOPS","JavaScript")
    private var knownPercentage = arrayListOf(225,255,270,300,330,300,270,180,225,350,135)
    private var wheelColor = arrayListOf(
        Color.parseColor("#00BFFF"),    //skyblue
        Color.BLUE,
        Color.BLUE,
        Color.GREEN,
        Color.GREEN,
        Color.GREEN,
        Color.BLUE,
        Color.parseColor("#FF0000"),    // orange
        Color.parseColor("#00BFFF"),    // skyblue
        Color.GREEN,
        Color.parseColor("#FF0000"))    // orange

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_technical_skill, container, false)
        explodeField = ExplosionField(context)

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }
        binding.idTechnicalRecycler.adapter = TechnicalAdapter(
            requireContext(),
            knownPercentage,
            language,
            requireActivity(),
            wheelColor
        )

        return binding.root
    }
}