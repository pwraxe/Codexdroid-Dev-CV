package com.codexdroid.codexdroiddevcv.fragments

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.FragmentViewFullImageBinding
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider


class ViewFullImageFragment : Fragment() {

    private lateinit var binding : FragmentViewFullImageBinding
    private var isPort = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_view_full_image, container, false)

        val image = arguments?.getString("IMG")

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }

        Glide.with(requireContext())
            .load(image)
            .apply(
                RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_error))
            .into(binding.idBigImageGif)


        binding.idRotateIcon.setOnClickListener{
            if(isPort){
                activity?.requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
                isPort = false
            }else{
                activity?.requestedOrientation = SCREEN_ORIENTATION_PORTRAIT
                isPort = true
            }

        }

        return binding.root

    }

}