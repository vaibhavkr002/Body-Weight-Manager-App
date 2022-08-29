package com.vaibhavkr.bodywieghtmanager

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class SignUpActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var db :FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
allrdyaccn.setOnClickListener(){
    var intent = Intent(this,LoginActivity::class.java)
    startActivity(intent)
    finish()
}
        auth= FirebaseAuth.getInstance()
        db= FirebaseFirestore.getInstance()


        sign_checkbox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (b) {
                passwordt.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                //    confirm_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                passwordt.setTransformationMethod(PasswordTransformationMethod.getInstance())
                //  confirm_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        })
       button_signup.setOnClickListener {
            if(checking())
            {

                val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy")
                val cal = Calendar.getInstance()
                val date = dateFormat.format(cal.time)

                var email=emailt.text.toString()
                var password= passwordt.text.toString()
                var name=name.text.toString()
                val user= hashMapOf(
                    "name" to name,
                    "email" to email
                )
                val Users=db.collection("USERS")
                val query =Users.whereEqualTo("email",email).get()
                    .addOnSuccessListener {
                            tasks->
                        if(tasks.isEmpty)
                        {
                            auth.createUserWithEmailAndPassword(email,password)
                                .addOnCompleteListener(this){
                                        task->
                                    if(task.isSuccessful)
                                    {
                                        Users.document(email).set(user)
                                        val intent=Intent(this,MainActivity::class.java)
                                        intent.putExtra("email",email)
                                        startActivity(intent)
                                        finish()
                                    }
                                    else
                                    {
                                        Toast.makeText(this,"Authentication Failed", Toast.LENGTH_LONG).show()
                                    }
                                }
                        }
                        else
                        {
                            Toast.makeText(this,"User Already Registered", Toast.LENGTH_LONG).show()
                            val intent= Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                        }
                    }
            }
            else{
                Toast.makeText(this,"Enter the Details", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun checking():Boolean{
        if(name.text.toString().trim{it<=' '}.isNotEmpty()
            && emailt.text.toString().trim{it<=' '}.isNotEmpty()
            &&passwordt.text.toString().trim{it<=' '}.isNotEmpty()
        )
        {
            return true
        }
        return false
    }
}