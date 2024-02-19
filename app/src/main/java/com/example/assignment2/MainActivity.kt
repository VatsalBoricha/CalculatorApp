package com.example.assignment2
/**
 * Name: Vatsal boricha
 * Student ID: 200528698
 * Date: 19  Feb, 2024
 * App Name: calculator
 * Version: v2.0 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var calculator: Calculator

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        calculator = Calculator(binding)


    }

}

