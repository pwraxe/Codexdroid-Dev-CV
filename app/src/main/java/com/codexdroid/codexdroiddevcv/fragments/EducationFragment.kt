package com.codexdroid.codexdroiddevcv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.adapters.EducationAdapter
import com.codexdroid.codexdroiddevcv.databinding.FragmentEducationBinding
import com.codexdroid.codexdroiddevcv.databinding.MenuEducationBinding
import com.codexdroid.codexdroiddevcv.databinding.MenuMyselfBinding
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import kotlinx.android.synthetic.main.menu_myself.view.*
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import tyrantgit.explosionfield.ExplosionField

class EducationFragment : Fragment(){

    private lateinit var binding : FragmentEducationBinding

    private lateinit var explodeField : ExplosionField

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_education, container, false)
        binding.idEducationRecycler.adapter = EducationAdapter(requireContext(), requireActivity())

        explodeField = ExplosionField(context)

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }

        return binding.root
    }

}