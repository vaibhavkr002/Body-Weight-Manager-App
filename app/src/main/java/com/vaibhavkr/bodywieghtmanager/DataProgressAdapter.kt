package com.vaibhavkr.bodywieghtmanager

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class DataProgressAdapter(val mCtx : Context, val layoutResId : Int, val mhsList :List<DataProgress2> ) :ArrayAdapter<DataProgress2> (mCtx, layoutResId, mhsList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(layoutResId, null)

        val tvName : TextView = view.findViewById(R.id.tv_nama)
        val tvAlien : TextView = view.findViewById(R.id.tv_alamat)
        val tvEdit : TextView = view.findViewById(R.id.tv_edit)



        val mahasiswa = mhsList[position]

        tvEdit.setOnClickListener{
            showUpdateDialog(mahasiswa)
        }


        tvName.text = mahasiswa.name
        tvAlien.text = mahasiswa.alien

        return view
    }

    fun showUpdateDialog(dataProgress2: DataProgress2) {
        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Edit Data")

        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.update_dialog, null)

        val et_weight= view.findViewById<EditText>(R.id.et_weight)
        val et_sum = view.findViewById<EditText>(R.id.et_sum)

        et_weight.setText(dataProgress2.name)
        et_sum.setText(dataProgress2.alien)

        builder.setView(view)

        builder.setPositiveButton("Update"){p0,p1 ->
            val dbMhs = FirebaseDatabase.getInstance().getReference("mahasiswa")

            val nama = et_weight.text.toString().trim()
            val alamat = et_sum.text.toString().trim()

            if(nama.isEmpty()){
              et_weight.error = "Error"
               et_weight.requestFocus()
                return@setPositiveButton
            }
            if(alamat.isEmpty()){
              et_sum.error = "Error"
               et_sum.requestFocus()
                return@setPositiveButton
            }


            val dataProgress2 = DataProgress2(dataProgress2.id, nama, alamat)


            dbMhs.child(dataProgress2.id).setValue(dataProgress2)

            Toast.makeText(mCtx, "Update", Toast.LENGTH_SHORT).show()

        }

        builder.setNeutralButton("no"){p0,p1 ->

        }


        builder.setNegativeButton("delete"){p0,p1 ->

            val dbMy =  FirebaseDatabase.getInstance().getReference("Mydata").child(dataProgress2.id)
            val dbM = FirebaseDatabase.getInstance().getReference("Data").child(dataProgress2.id)

            dbM.removeValue()
            dbMy.removeValue()


        }

        val alert = builder.create()
        alert.show()
    }
}