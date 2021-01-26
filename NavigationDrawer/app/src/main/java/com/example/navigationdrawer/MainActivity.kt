package com.example.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InitializeLayout()

        nav_view.setNavigationItemSelectedListener(this)
    }
    fun InitializeLayout(){
        //toolbar를 통해 App bar 생성
        setSupportActionBar(toolbar)

        //App bar 좌측 Drawer를 열기위한 아이콘 생성
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_forward_ios_24)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.oepn, R.string.closed)

        drawer_layout.addDrawerListener(actionBarDrawerToggle)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuitem1 -> Toast.makeText(getApplicationContext(), "SelectedItem 1", Toast.LENGTH_SHORT).show()
            R.id.menuitem2 -> Toast.makeText(getApplicationContext(), "SelectedItem 2", Toast.LENGTH_SHORT).show()
            R.id.menuitem3 -> Toast.makeText(getApplicationContext(), "SelectedItem 3", Toast.LENGTH_SHORT).show()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }
}



