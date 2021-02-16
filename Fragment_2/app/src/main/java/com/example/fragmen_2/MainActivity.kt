package com.example.fragmen_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListFragment.ImageSelectionCallBack {
    lateinit var listFragment: ListFragment
    lateinit var viewerFragment: ViewerFragment

    val images = arrayOf(R.drawable.ic_baseline_brightness_1_24, R.drawable.ic_baseline_brightness_2_24, R.drawable.ic_baseline_brightness_3_24)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager
        listFragment = manager.findFragmentById(R.id.listFragment) as ListFragment
        viewerFragment = manager.findFragmentById(R.id.viewerFragment) as ViewerFragment
    }

    override fun onImageSelected(position: Int) {
        viewerFragment.setImage(images[position])
    }
}