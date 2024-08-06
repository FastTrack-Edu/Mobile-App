package com.nizamsetiawan.app.fasttrackedu.views.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivityLoginBinding
import com.nizamsetiawan.app.fasttrackedu.utils.CustomToast
import com.nizamsetiawan.app.fasttrackedu.utils.Prefs
import com.nizamsetiawan.app.fasttrackedu.utils.ResponseState
import com.nizamsetiawan.app.fasttrackedu.views.BottomNavigationBarActivity
import com.nizamsetiawan.app.fasttrackedu.views.auth.viewmodels.AuthViewModels
import org.koin.android.ext.android.inject

class LoginActivity : CoreActivity<ActivityLoginBinding>() {
    private val authViewModel: AuthViewModels by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            setupButtonAction()
            initObservers()
            edtEmail.inputText = "user1@example.com"
            edtPassword.inputText = "user12345"

//            edtEmail.inputText = Prefs.getEmail
//            edtPassword.inputText = Prefs.getPassword
        }
    }

    private fun ActivityLoginBinding.setupButtonAction() {
        tvBtnRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        btnSignIn.setOnClickListener {
            val emailField = edtEmail.inputText.trim()
            val passField = edtPassword.inputText.trim()


            if (emailField.isEmpty()) {
                getInformToast("Email wajib diisi")
            }
            if (passField.isEmpty()) {
                getInformToast("Password wajib diisi")
            }
            if (emailField.isNotEmpty() && passField.isNotEmpty()) {
                authViewModel.login(emailField, passField)
                if (cbRememberMe.isChecked) {
                    Prefs.setRememberMe(emailField, passField)
                } else {
                    Prefs.clearRememberMe()
                }
            }
        }
    }

    private fun ActivityLoginBinding.initObservers() {
        authViewModel.loginResult.observe(this@LoginActivity) { response ->
            when (response) {
                is ResponseState.Loading -> {
                    enabledComponent(false)
                    getInformToast("Memproses dan memeriksa akun...")
                }

                is ResponseState.Success -> {
                    enabledComponent(true)
                    CustomToast.successToast(
                        "Berhasil Masuk!",
                        this@LoginActivity
                    )
                    startActivity(
                        Intent(
                            this@LoginActivity,
                            BottomNavigationBarActivity::class.java
                        )
                    )
                    finish()
                }

                is ResponseState.Error -> {
                    enabledComponent(true)
                    CustomToast.errorToast(response.errorMessage, this@LoginActivity)
                }
            }
        }
    }
    private fun ActivityLoginBinding.enabledComponent(isComponentEnabled: Boolean) {
        edtEmail.isEnabled = isComponentEnabled
        edtPassword.isEnabled = isComponentEnabled
        btnSignIn.isEnabled = isComponentEnabled
        tvBtnRegister.isEnabled = isComponentEnabled
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    private fun getInformToast(msg: String) {
        CustomToast.informToast(msg, this)
    }

}