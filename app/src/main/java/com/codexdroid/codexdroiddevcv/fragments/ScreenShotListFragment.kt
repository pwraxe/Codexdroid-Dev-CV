package com.codexdroid.codexdroiddevcv.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.adapters.ProjectAdapter
import com.codexdroid.codexdroiddevcv.databinding.FragmentScreenShotListBinding
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder


class ScreenShotListFragment : Fragment() {

    private lateinit var binding : FragmentScreenShotListBinding

    private var androidImages = arrayListOf(
        "https://codexdroid.com/images/portfolio/app_AxeBankOutput.gif",
        "https://codexdroid.com/images/portfolio/app_DB_Output.gif",
        "https://codexdroid.com/images/portfolio/app_ResumeOutput.gif",
        "https://codexdroid.com/images/portfolio/app_TEIT_Output.gif")

    private var desktopImage = arrayListOf(
        "https://codexdroid.com/images/portfolio/java_ExamTest_Output.gif",
        "https://codexdroid.com/images/portfolio/java_UserDetailFormOutput.gif",
        "https://codexdroid.com/images/portfolio/java_searchEmpOutput.gif",
        "https://codexdroid.com/images/portfolio/java_EMSOutput.gif")

    private var webImage = arrayListOf(
        "https://codexdroid.com/images/portfolio/mini_proj_ss1.png",
        "https://codexdroid.com/images/portfolio/mini_proj_ss2.png",
        "https://codexdroid.com/images/portfolio/mini_proj_ss3.png")


    private var androidProjectDesc = arrayListOf(
        "Bank Application as a Mini Project",
        "SQLite CRUD Operation",
        "On first try Single Page Resume",
        "Converted Third Year Mini WebBase Project into Android Application for more practice")

    private var desktopProjectDesc = arrayListOf(
        "Creating Desktop base Test Series in Java Swing",
        "Complete Registration Detail to Store data in MySQL Database",
        "Search Employee from given list of Employees",
        "Employee Management System in Java for Mini Project")

    private var websiteProjectDesc = arrayListOf(
        "Third Year Mini Project Registration Form",
        "Third Year Mini Project Admin Login Page",
        "Third Year Mini Project get Student detail base on Mobile Number")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_screen_shot_list, container, false)

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }


           // get number & send data to recycler with when condition
           when(arguments?.getInt("NUM",0)){
               1 -> { binding.idProjectRecycler.adapter = ProjectAdapter(requireContext(),androidImages, androidProjectDesc, requireActivity()) }
               2 -> { binding.idProjectRecycler.adapter = ProjectAdapter(requireContext(),desktopImage, desktopProjectDesc, requireActivity()) }
               3 -> { binding.idProjectRecycler.adapter = ProjectAdapter(requireContext(),webImage, websiteProjectDesc, requireActivity()) }
               else -> { Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show() }
           }


        return binding.root
    }

}