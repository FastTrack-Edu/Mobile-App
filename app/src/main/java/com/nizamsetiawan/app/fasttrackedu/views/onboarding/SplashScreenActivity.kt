package com.nizamsetiawan.app.fasttrackedu.views.onboarding

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.nizamsetiawan.app.fasttrackedu.R
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivitySplashScreenBinding
import com.nizamsetiawan.app.fasttrackedu.utils.Constant
import com.nizamsetiawan.app.fasttrackedu.utils.Prefs
import com.nizamsetiawan.app.fasttrackedu.views.BottomNavigationBarActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : CoreActivity<ActivitySplashScreenBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        playAnimation()
        splashScreenTransition()
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivitySplashScreenBinding =
        ActivitySplashScreenBinding.inflate(layoutInflater)


    private fun splashScreenTransition() {
        lifecycleScope.launch {
            delay(Constant.SPLASH_SCREEN_DURATION.seconds)
            if (Prefs.getToken.isEmpty()) {
                startActivity(Intent(this@SplashScreenActivity, OnboardingActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashScreenActivity, BottomNavigationBarActivity::class.java))
                finish()
            }
        }
    }

    private fun playAnimation() {
        binding.apply {
            AnimatorSet().apply {
                playSequentially(
                    ObjectAnimator.ofFloat(ivSplashScreen, View.ALPHA, 1f).setDuration(700),
                    ObjectAnimator.ofFloat(tvAppName, View.ALPHA, 1f).setDuration(500),
                    ObjectAnimator.ofFloat(tvAppFullName, View.ALPHA, 1f).setDuration(500),
                )
                start()
            }
        }
    }
}