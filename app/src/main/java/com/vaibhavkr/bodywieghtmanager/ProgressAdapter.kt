package com.vaibhavkr.bodywieghtmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ProgressAdapter(val mCtx : Context, val layoutResId : Int, val matkulList :List<DataProgress> ) : ArrayAdapter<DataProgress>(mCtx, layoutResId, matkulList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(layoutResId, null)

        val tv1 = view.findViewById<TextView>(R.id.tv1)
        val tv2 = view.findViewById<TextView>(R.id.tv2)

        val matkul = matkulList[position]

        tv1.text = matkul.name
        tv2.text = matkul.age.toString()

        return view

    }
}