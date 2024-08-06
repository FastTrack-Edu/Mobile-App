package com.nizamsetiawan.app.fasttrackedu.views.auth

import android.os.Bundle
import android.view.LayoutInflater
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivityTermsnServiceBinding
import com.nizamsetiawan.app.fasttrackedu.utils.CustomToast

class TermsnServiceActivity  : CoreActivity<ActivityTermsnServiceBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
           buttonCancel.setOnClickListener {
               finish()
           }
            buttonAgree.setOnClickListener {
                getInformToast("Terms and Service Accepted")
                finish()
            }
        }
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityTermsnServiceBinding =
        ActivityTermsnServiceBinding.inflate(layoutInflater)

    private fun getInformToast(msg: String) {
        CustomToast.informToast(msg, this)
    }
}