package com.vaibhavkr.bodywieghtmanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_workout.*

class ProgressActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etweight : EditText
    private lateinit var etsum : EditText
    private lateinit var btnSave : Button
    private lateinit var lv : ListView

    private lateinit var ref : DatabaseReference
    private lateinit var dataList: MutableList<DataProgress2>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        ref = FirebaseDatabase.getInstance().getReference("Data")

        etweight = findViewById(R.id.et_weight)
        etsum = findViewById(R.id.et_sum)
        btnSave = findViewById(R.id.btn_save)
        lv = findViewById(R.id.lv)
        btnSave.setOnClickListener(this)


        arrow_left.setOnClickListener(){
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }

        dataList = mutableListOf()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    dataList.clear()
                    for (h in p0.children) {
                        val dataProgress2 = h.getValue(DataProgress2::class.java)
                        if (dataProgress2 != null) {
                            dataList.add(dataProgress2)
                        }
                    }

                    val adapter =
                        DataProgressAdapter(this@ProgressActivity, R.layout.item_list, dataList)
                    lv.adapter = adapter
                }
            }

        })
    }

    override fun onClick(v: View?) {
       saveData()
    }

    private fun saveData(){
        val weight = etweight.text.toString().trim()
        val sum = etsum.text.toString().trim()

        if(weight.isEmpty()){
            etweight.error = "Error!"
            return
        }

        if(sum.isEmpty()){
            etsum.error = "Error!"
            return
        }



        val myId = ref.push().key

        val mhs = DataProgress2(myId!!,weight,sum)

        if (myId != null) {
            ref.child(myId).setValue(mhs).addOnCompleteListener{

            }
        }
    }
}
