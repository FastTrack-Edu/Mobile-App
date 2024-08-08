package com.nizamsetiawan.app.fasttrackedu.views.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivityPaymentVideoLessonBinding

class PaymentVideoLessonActivity : CoreActivity<ActivityPaymentVideoLessonBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun setupBinding(layoutInflater: LayoutInflater): ActivityPaymentVideoLessonBinding =
        ActivityPaymentVideoLessonBinding.inflate(layoutInflater)
}

