package com.nizamsetiawan.app.fasttrackedu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivityMainBinding
import com.nizamsetiawan.app.fasttrackedu.views.auth.SplashScreenActivity

class MainActivity : CoreActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@MainActivity, SplashScreenActivity::class.java))
        finish()
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)
}