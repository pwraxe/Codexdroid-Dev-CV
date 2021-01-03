package com.codexdroid.codexdroiddevcv.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.adapters.CertificateAdapter
import com.codexdroid.codexdroiddevcv.databinding.FragmentCertificatesBinding
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer2
import tyrantgit.explosionfield.ExplosionField

class CertificatesFragment : Fragment(){

    private lateinit var binding : FragmentCertificatesBinding
    private lateinit var explodeField : ExplosionField

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_certificates, container, false)

        explodeField = ExplosionField(context)

        binding.idCertViewPager.adapter = CertificateAdapter(requireContext(),requireActivity())

        binding.idCertViewPager.setPageTransformer(BookFlipPageTransformer2())

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }

        return binding.root
    }

}