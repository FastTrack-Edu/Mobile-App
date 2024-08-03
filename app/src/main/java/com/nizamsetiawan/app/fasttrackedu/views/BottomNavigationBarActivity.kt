package com.nizamsetiawan.app.fasttrackedu.views

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nizamsetiawan.app.fasttrackedu.R
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivityBottomNavigationBarBinding

class BottomNavigationBarActivity : CoreActivity<ActivityBottomNavigationBarBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            setupBottomNavigationBar()

        }
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityBottomNavigationBarBinding =
        ActivityBottomNavigationBarBinding.inflate(layoutInflater)

    private fun ActivityBottomNavigationBarBinding.setupBottomNavigationBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.hide()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_host_fragment_activity_bottom_navigation_bar) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_notifications, R.id.navigation_lms,R.id.navigation_event, R.id.navigation_dashboard, R.id.navigation_profile)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}