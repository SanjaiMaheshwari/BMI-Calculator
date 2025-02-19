package com.example.bmicalculator

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val weight = binding.weightValue
        val height = binding.heightValue


        binding.imageBoy.setOnClickListener {
            binding.imageBoy.setImageResource(R.drawable.ic_boy)
            binding.imageGirl.setImageResource(R.drawable.ic_girl_blur)
        }

        binding.imageGirl.setOnClickListener {
            binding.imageBoy.setImageResource(R.drawable.ic_boy_blur)
            binding.imageGirl.setImageResource(R.drawable.ic_girl)
        }



        binding.calculateButton.setOnClickListener {
            var weightValue = 0.0
            var heightvalue = 0.0

            if (weight.text.toString().isNotEmpty()) {
                weightValue = weight.text.toString().toDouble()
            }

            if (height.text.toString().isNotEmpty()) {
                heightvalue = (height.text.toString().toDouble() / 100)
            }

            if (weightValue > 0.0 && heightvalue > 0.0) {
                val bmiValue = String.format("%.2f", weightValue / heightvalue.pow(2))
                binding.bmi.text = bmiValue
                binding.bmiStatus.text = bmiStatusValue(weightValue / heightvalue.pow(2))
                binding.bmiView.visibility = VISIBLE
                binding.calculateButton.visibility = GONE
            }
            else
                Toast.makeText(this, "Please Input Weight and Height values greater than 0 ", Toast.LENGTH_LONG).show()
        }

        binding.calculateAgain.setOnClickListener {
            binding.bmiView.visibility = GONE
            binding.calculateButton.visibility = VISIBLE
            weight.text.clear()
            height.text.clear()
            weight.requestFocus()
        }

    }



    private fun bmiStatusValue(bmi: Double): String {
        lateinit var bmiStatus: String
        if (bmi < 18.5)
            bmiStatus = "UnderWeight"
        else if (bmi > 18.5 && bmi < 25)
            bmiStatus = "Normal"
        else if (bmi >= 25 && bmi < 30)
            bmiStatus = " Over Weight"
        else if (bmi > 30)
            bmiStatus = "Obese"
        return bmiStatus
    }

}