package com.codexdroid.codexdroiddevcv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.adapters.InterestPagerAdapter
import com.codexdroid.codexdroiddevcv.databinding.FragmentInterestBinding
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer2
import tyrantgit.explosionfield.ExplosionField

class InterestFragment : Fragment(){


    private lateinit var binding : FragmentInterestBinding

    private lateinit var explodeField : ExplosionField

    private lateinit var homeFragment: HomeFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_interest, container, false)

        explodeField = ExplosionField(context)

        homeFragment = HomeFragment()


        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }

        val adapter = InterestPagerAdapter(requireContext(),requireActivity())
        binding.idInterestViewPager.adapter = adapter
        val cardFlip = CardFlipPageTransformer2()
        cardFlip.isScalable = true
        binding.idInterestViewPager.setPageTransformer(cardFlip)

        return binding.root
    }
}