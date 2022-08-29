package com.vaibhavkr.bodywieghtmanager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

login.setOnClickListener(){
    var intent = Intent(this,LoginActivity::class.java)
    startActivity(intent)

}
        signup.setOnClickListener(){
            var intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)

        }

    }
}