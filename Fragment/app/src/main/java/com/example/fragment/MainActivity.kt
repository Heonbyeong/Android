package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var mainFragment: MainFragment
    private lateinit var menuFragment: MenuFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainFragment = supportFragmentManager.findFragmentById(R.id.mainFragment) as MainFragment
        menuFragment = MenuFragment()
    }

    fun onFragmentChanged(index : Int){
        when(index){
            0 -> supportFragmentManager.beginTransaction().replace(R.id.container, menuFragment).commit()
            1 -> supportFragmentManager.beginTransaction().replace(R.id.container, mainFragment).commit()
        }
    }
}