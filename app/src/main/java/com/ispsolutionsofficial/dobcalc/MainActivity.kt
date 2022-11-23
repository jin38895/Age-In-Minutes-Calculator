package com.ispsolutionsofficial.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate:TextView? = null
    private var tvAgeInMinutes:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectDate:Button = findViewById(R.id.btnSelectDate)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnSelectDate.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
//        Toast.makeText(this,"It is working", Toast.LENGTH_LONG).show()
        val myCalendar:Calendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, {view, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.text = selectedDate

            val sfd = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val selectedDateInMinutes = sfd.parse(selectedDate).time / 60000
            val currentDateInMinutes = sfd.parse(sfd.format(System.currentTimeMillis())).time / 60000
            val ageInMinutes = currentDateInMinutes - selectedDateInMinutes
            tvAgeInMinutes?.text = ageInMinutes.toString()

        }, year, month, day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}