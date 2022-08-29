package com.vaibhavkr.bodywieghtmanager

data class DataProgress(
    val id : String,
    val name: String,
    val age : Int
){
    constructor(): this("","",0){

    }
}