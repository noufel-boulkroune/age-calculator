package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var btnDatePicker: Button
    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the button after setContentView
        btnDatePicker = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)



        // Set click listener for the button separately
        btnDatePicker.setOnClickListener {
            // Call method to handle date picker click
            clickDatePicker()
            // Perform other actions related to button click here
        }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDayInMinutes = theDate?.time?.div(60000) ?: 0 // Convert selected date to minutes

                val currentDate = Calendar.getInstance().time // Get current date
                val currentDateInMinutes = currentDate.time / 60000 // Convert current date to minutes

                val theDifferenceInMinutes = currentDateInMinutes - selectedDayInMinutes
                tvAgeInMinutes?.text = theDifferenceInMinutes.toString()
               // Toast.makeText(applicationContext, "Year $selectedYear,  month ${selectedMonth+1}, day ${selectedDayOfMonth}", Toast.LENGTH_SHORT).show()
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
