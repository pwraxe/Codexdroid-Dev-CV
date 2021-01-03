package com.codexdroid.codexdroiddevcv.adapters

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.ProjectRackBinding

class ProjectAdapter(var context: Context,
                     var screenShots : ArrayList<String>,
                     var projectDesc : ArrayList<String>,
                     var activity: Activity) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {


    class ViewHolder(var binding : ProjectRackBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProjectRackBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = screenShots.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(screenShots[position])
            .apply(RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_error))
            .into(holder.binding.idGifImage)

        holder.binding.idProjectDesc.text = projectDesc[position]

        holder.binding.idImageCardContainer.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("IMG",screenShots[position])
            it.findNavController().navigate(R.id.action_screenShotListFragment_to_viewFullImageFragment,bundle)
        }
    }
}