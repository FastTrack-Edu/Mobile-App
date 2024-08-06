package com.nizamsetiawan.app.fasttrackedu.views.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.nizamsetiawan.app.fasttrackedu.core.CoreActivity
import com.nizamsetiawan.app.fasttrackedu.databinding.ActivityRegisterBinding
import com.nizamsetiawan.app.fasttrackedu.utils.CustomToast
import com.nizamsetiawan.app.fasttrackedu.utils.ResponseState
import com.nizamsetiawan.app.fasttrackedu.views.auth.viewmodels.AuthViewModels
import org.koin.android.ext.android.inject

class RegisterActivity : CoreActivity<ActivityRegisterBinding>() {

    private val authViewModel: AuthViewModels by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            tvBtnLogin.setOnClickListener {
                intentSignIn()
            }
            tvTermsConditions.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, TermsnServiceActivity::class.java))
            }
            formValidation()
            setupObserver()
        }
    }

    private fun ActivityRegisterBinding.formValidation() {
        btnRegister.setOnClickListener {
            val nameField = edtName.inputText.trim()
            val emailField = edtEmail.inputText.trim()
            val passwordField = edtPassword.inputText.trim()
            val confirmPasswordField = confirmPassword.inputText.trim()


            if (nameField.isEmpty()) {
                getInformToast("Jangan biarkan kolom Nama kosong")
            }
            if (emailField.isEmpty()) {
                getInformToast("Jangan biarkan kolom Email kosong")
            }
            if (passwordField.isEmpty()) {
                getInformToast("Jangan biarkan kolom Kata Sandi kosong")
            }
            if (confirmPasswordField.isEmpty()) {
                getInformToast("Jangan biarkan kolom Konfirmasi Kata Sandi kosong")
            }
            if (passwordField != confirmPasswordField) {
                getInformToast("Kata sandi tidak sama")
            }

            if (emailField.isNotEmpty() && passwordField.isNotEmpty() && confirmPasswordField.isNotEmpty()) {
                authViewModel.register(nameField, emailField, passwordField, confirmPasswordField)
            }
        }
    }

    private fun ActivityRegisterBinding.setupObserver() {
        authViewModel.registerResult.observe(this@RegisterActivity) { response ->
            when (response) {
                is ResponseState.Loading -> {
                    CustomToast.informToast(
                        "Memproses informasi registrasi akun...",
                        this@RegisterActivity
                    )
                    isComponentEnabled(false)
                }

                is ResponseState.Success -> {
                    edtPassword.inputText = ""
                    confirmPassword.inputText = ""
                    edtEmail.inputText = ""
                    isComponentEnabled(true)
                    CustomToast.successToast(
                        "Sukses melakukan registrasi pengguna !",
                        this@RegisterActivity
                    )
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    finish()
                }

                is ResponseState.Error -> {
                    isComponentEnabled(true)
                    CustomToast.errorToast(response.errorMessage, this@RegisterActivity)
                }
            }
        }
    }

    private fun ActivityRegisterBinding.isComponentEnabled(isEnable: Boolean) {
        btnRegister.isEnabled = isEnable
        tvBtnLogin.isEnabled = isEnable
        edtEmail.isEnabled = isEnable
        edtName.isEnabled = isEnable
        edtPassword.isEnabled = isEnable
        confirmPassword.isEnabled = isEnable
        tvTermsConditions.isEnabled = isEnable
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityRegisterBinding =
        ActivityRegisterBinding.inflate(layoutInflater)

    private fun intentSignIn() {
        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }

    private fun getInformToast(msg: String) {
        CustomToast.informToast(msg, this@RegisterActivity)
    }


}