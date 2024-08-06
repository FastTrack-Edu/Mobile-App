package com.nizamsetiawan.app.fasttrackedu.views.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.nizamsetiawan.app.fasttrackedu.R
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivityOnboardingBinding
import com.nizamsetiawan.app.fasttrackedu.views.auth.LoginActivity
import com.nizamsetiawan.app.fasttrackedu.views.onboarding.adapter.OnboardingAdapter
import com.nizamsetiawan.app.fasttrackedu.views.onboarding.model.OnboardingModel

class OnboardingActivity : CoreActivity<ActivityOnboardingBinding>() {

    private val onboardingAdapter = OnboardingAdapter(
        listOf(
            OnboardingModel(
                "Tingkatkan Prestasimu Dengan Pendampingan",
                "Dapatkan pendampingan khusus untuk tingkatkan prestasimu dengan pendamping ahli pada bidangnya",
                R.drawable.splash1
            ),
            OnboardingModel(
                "Buka Potensi Keahlihan Menulis Anda Pada Ahilnya",
                "Tingkatkan keahlian menulis anda dengan layanan pembelajaran video yang menarik dan menyenangkanada bidangnya",
                R.drawable.splash2
            ),
            OnboardingModel(
                "Optimalkan Produktivitas Dengan Kursus Langsung",
                "Dengan adanya kursus langsung akan membantu dalam produktivitasmu secara optimal",
                R.drawable.splash3
            ),
            OnboardingModel(
                "Tingkatkan Daya Saing Anda dengan Mentor Pribadi",
                "Fitur Mentoring Private akan membantu dalam menyempurnakan prestasimu",
                R.drawable.splash4
            )
        )
    )

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityOnboardingBinding {
        return ActivityOnboardingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()
        setupIndicator()
        setupListeners()
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = onboardingAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
    }

    private fun setupIndicator() {
        val indicatorContainer = binding.indicatorContainer
        val indicators = arrayOfNulls<ImageView>(onboardingAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(8, 0, 8, 0)
        }
        for (i in indicators.indices) {
            indicators[i] = ImageView(this).apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        this@OnboardingActivity,
                        R.drawable.indicator_inactive
                    )
                )
                this.layoutParams = layoutParams
            }
            indicatorContainer.addView(indicators[i])
        }
        setCurrentIndicator(0)
    }

    private fun setupListeners() {
        binding.btnSelanjutnya.setOnClickListener {
            if (binding.viewPager.currentItem + 1 < onboardingAdapter.itemCount) {
                binding.viewPager.currentItem += 1
            } else {
                navigateToMainActivity()
            }
        }
        binding.tvLewati.setOnClickListener {
            navigateToMainActivity()
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val indicatorContainer = binding.indicatorContainer
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer[i] as ImageView
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    if (i == index) R.drawable.indicator_active else R.drawable.indicator_inactive
                )
            )
        }
    }

    private fun navigateToMainActivity() {
        Intent(applicationContext, LoginActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}
