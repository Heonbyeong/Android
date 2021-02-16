package com.example.fragmen_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ViewerFragment : Fragment() {
    lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_viewer, container, false)

        imageView = rootView.findViewById(R.id.imageView)
        return rootView
    }

    fun setImage(resId: Int){
        imageView.setImageResource(resId)
    }
}