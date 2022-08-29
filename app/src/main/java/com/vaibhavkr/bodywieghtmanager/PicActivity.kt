package com.vaibhavkr.bodywieghtmanager

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_pic.*

class PicActivity : AppCompatActivity() {
    lateinit var ImageUri: Uri
val ImageBack = 1
    lateinit var storage: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pic)

      storage = FirebaseStorage.getInstance().getReference().child("ImageFolder")

//        upload.setOnClickListener(){
//            val intent = Intent(Intent.ACTION_GET_CONTENT)
//            intent.setType("image/*")
//            startActivityForResult(intent,ImageBack)
//        }


        select.setOnClickListener(){

            selectImage()
        }

        upload.setOnClickListener(){
            uploadImage()
        }
    }

    private fun uploadImage() {

        if(ImageUri!=null){
            val progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Uploading file...")
        progressDialog.setCancelable(false)
        progressDialog.show()

            val storageReference = FirebaseStorage.getInstance().reference.child("images/pic.jpg")

            storageReference.putFile(ImageUri).
                addOnCompleteListener{
                    pic.setImageURI(null)
                    Toast.makeText(this,"SucessFully uploaded", Toast.LENGTH_SHORT).show()
                    if(progressDialog.isShowing)progressDialog.dismiss()
                }.addOnFailureListener {

                if (progressDialog.isShowing) progressDialog.dismiss()
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectImage() {
      val intent = Intent()
        intent.type="image/*"
        intent.action = Intent.ACTION_GET_CONTENT
startActivityForResult(Intent.createChooser(intent,"Choose Result"),111)
        startActivityForResult(intent,100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==111 && resultCode== Activity.RESULT_OK && data !=null){
            ImageUri = data?.data!!
            pic.setImageURI(ImageUri)

        }
    }
    }


