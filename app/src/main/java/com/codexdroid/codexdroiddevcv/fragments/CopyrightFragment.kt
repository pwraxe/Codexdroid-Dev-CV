package com.codexdroid.codexdroiddevcv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.FragmentCopyrightBinding
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import tyrantgit.explosionfield.ExplosionField

class CopyrightFragment : Fragment(){


    private lateinit var binding : FragmentCopyrightBinding

    private lateinit var explodeField : ExplosionField

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_copyright, container, false)

        explodeField = ExplosionField(context)

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }

        binding.idCopyrightChip.isSelected = true
        binding.idCopyrightLayout.visibility = View.VISIBLE
        binding.privacyLayout.visibility = View.GONE

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){
                    binding.idCopyrightChip.id -> {
                        binding.idCopyrightLayout.visibility = View.VISIBLE
                        binding.privacyLayout.visibility = View.GONE
                    }
                    binding.idPrivacyChip.id -> {
                        binding.idCopyrightLayout.visibility = View.GONE
                        binding.privacyLayout.visibility = View.VISIBLE
                    }
                }
        }


        return binding.root
    }
}