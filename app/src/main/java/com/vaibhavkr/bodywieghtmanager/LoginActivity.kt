package com.vaibhavkr.bodywieghtmanager

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= FirebaseAuth.getInstance()
      dntaccn.setOnClickListener {
            var intent =Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }



        login_checkbox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (b) {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                //    confirm_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance())
                //  confirm_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        })





       button_login.setOnClickListener {
            if(checking()){
                val email=Emailid.text.toString()
                val password= password.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            var intent =Intent(this,MainActivity::class.java)
                            intent.putExtra("email",email)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Wrong Details", Toast.LENGTH_LONG).show()
                        }
                    }
            }
            else{
                Toast.makeText(this,"Enter the Details",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun checking():Boolean
    {
        if(Emailid.text.toString().trim{it<=' '}.isNotEmpty()
            && password.text.toString().trim{it<=' '}.isNotEmpty())
        {
            return true
        }
        return false
    }
}