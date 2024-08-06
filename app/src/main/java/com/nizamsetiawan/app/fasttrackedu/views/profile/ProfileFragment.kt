package com.nizamsetiawan.app.fasttrackedu.views.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nizamsetiawan.app.fasttrackedu.core.CoreFragment
import com.nizamsetiawan.app.fasttrackedu.databinding.FragmentProfileBinding
import com.nizamsetiawan.app.fasttrackedu.views.auth.LoginActivity
import com.nizamsetiawan.app.fasttrackedu.views.auth.viewmodels.AuthViewModels
import org.koin.android.ext.android.inject


class ProfileFragment: CoreFragment<FragmentProfileBinding>() {
    private val authViewModel: AuthViewModels by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
           btnLogout.setOnClickListener {
               authViewModel.logout()
               startActivity(Intent(requireActivity(), LoginActivity::class.java).apply {
                   flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
               })
               requireActivity().finish()           }
        }
    }

    override fun setupFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)




}