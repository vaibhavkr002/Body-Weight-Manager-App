package com.vaibhavkr.bodywieghtmanager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPref=this?.getPreferences(MODE_PRIVATE)?:return
        val isLogin=sharedPref.getString("email","1")



report.setOnClickListener(){
    var intent = Intent(this,BmiActivity::class.java)
    startActivity(intent)

}


       progress.setOnClickListener(){
            var intent = Intent(this,ProgressActivity::class.java)
           startActivity(intent)

        }


        workouts.setOnClickListener(){
            var intent = Intent(this,WorkoutActivity::class.java)
            startActivity(intent)

        }
       wloss.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }
        gainw.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }
        fullbody.setOnClickListener(){
            var intent = Intent(this,ShowWorkout::class.java)
            startActivity(intent)

        }
 stopwatch.setOnClickListener(){
     var intent = Intent(this,StopwatchActivity::class.java)
            startActivity(intent)

        }
      menu.setOnClickListener {
            sharedPref.edit().remove("email").apply()
            var intent = Intent(this,WelcomeActivity::class.java)
            startActivity(intent)
            finish()
       }
        if(isLogin=="1")
        {
            var email=intent.getStringExtra("email")
            if(email!=null)
            {
                setText(email)
                with(sharedPref.edit())
                {
                    putString("email",email)
                    apply()
                }
            }
            else{
                var intent = Intent(this,WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else
        {
            setText(isLogin)
        }

    }



    private fun setText(email:String?)
    {
        db= FirebaseFirestore.getInstance()
        if (email != null) {
            db.collection("USERS").document(email).get()
                .addOnSuccessListener {
                        tasks->
                    name.text=tasks.get("name").toString()
                   // emailLog.text=tasks.get("email").toString()

                }
        }

    }

}




