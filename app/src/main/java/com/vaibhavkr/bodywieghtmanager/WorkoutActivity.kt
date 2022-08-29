package com.vaibhavkr.bodywieghtmanager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_workout.*

class WorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        arrow_left.setOnClickListener(){
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }

        a1.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

        a2.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

        a3.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

        a4.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

        a5.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

        a6.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

        a7.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

        a8.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

        a9.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

        a10.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

        a11.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

        a12.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }

    }
}