package com.example.writeeverything

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.ActionBar


class MainActivity2 : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        navController = findNavController(R.id.fragmentContainerView)
        setupActionBarWithNavController(navController)
        // Define ActionBar object
        val actionBar: ActionBar?
        actionBar = supportActionBar

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        val colorDrawable = ColorDrawable(Color.parseColor("#ff5151"))

        // Set BackgroundDrawable
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(colorDrawable)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}