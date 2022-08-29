package com.vaibhavkr.bodywieghtmanager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_workout.*

class ShowWorkout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_workout)

        arrow_left.setOnClickListener(){
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}