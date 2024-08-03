package com.nizamsetiawan.app.fasttrackedu.utils

import android.content.Context
import com.nizamsetiawan.app.fasttrackedu.R
import com.shashank.sony.fancytoastlib.FancyToast

object CustomToast {
    fun successToast(msg:String,context:Context){
        FancyToast.makeText(context,msg,FancyToast.LENGTH_LONG,FancyToast.SUCCESS, R.drawable.ic_toast,false).show()
    }

    fun errorToast(msg:String,context:Context){
        FancyToast.makeText(context,msg,FancyToast.LENGTH_LONG,FancyToast.ERROR, R.drawable.ic_toast,false).show()
    }

    fun informToast(msg: String,context:Context){
        FancyToast.makeText(context,msg,FancyToast.LENGTH_LONG,FancyToast.INFO, R.drawable.ic_toast,false).show()
    }
}