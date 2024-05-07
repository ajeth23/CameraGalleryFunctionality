package com.ecuacion.reconi_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btm_navigation = findViewById<BottomNavigationView>(R.id.btm_nav)
        val nav_controller = Navigation.findNavController(this, R.id.host_fragment)

        NavigationUI.setupWithNavController(btm_navigation, nav_controller)


    }


}