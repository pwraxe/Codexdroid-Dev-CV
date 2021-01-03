package com.codexdroid.codexdroiddevcv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.FragmentProjectsBinding
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import tyrantgit.explosionfield.ExplosionField

class ProjectsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding : FragmentProjectsBinding
    private lateinit var explodeField : ExplosionField

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_projects, container, false)

        explodeField = ExplosionField(context)

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }

        binding.idAndroidCard.setOnClickListener(this)
        binding.idDesktopCard.setOnClickListener(this)
        binding.idWebsiteCard.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {
        val bundle = Bundle()
        when(v?.id){
            R.id.id_androidCard -> {
                bundle.putInt("NUM",1)
                findNavController().navigate(R.id.action_projectsFragment_to_screenShotListFragment,bundle)
            }
            R.id.id_desktopCard -> {
                bundle.putInt("NUM",2)
                findNavController().navigate(R.id.action_projectsFragment_to_screenShotListFragment,bundle)
            }
            R.id.id_websiteCard -> {
                bundle.putInt("NUM",3)
                findNavController().navigate(R.id.action_projectsFragment_to_screenShotListFragment,bundle)
            }
        }
    }

}