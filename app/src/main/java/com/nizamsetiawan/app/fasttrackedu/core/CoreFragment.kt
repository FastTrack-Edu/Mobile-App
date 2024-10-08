package com.nizamsetiawan.app.fasttrackedu.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.nizamsetiawan.app.fasttrackedu.utils.Prefs

abstract class CoreFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Prefs.init(requireContext())
        _binding = setupFragmentBinding(inflater, container, savedInstanceState)
        return _binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun setupFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

//    protected fun setupToolbar(toolbar: MaterialToolbar, onNavigationClick: (() -> Unit)? = null) {
//        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
//        toolbar.apply {
//            setNavigationIcon(R.drawable.ic_arrow_back)
//            setNavigationOnClickListener {
//                onNavigationClick?.invoke() ?: (activity as? AppCompatActivity)?.onBackPressed()
//            }
//        }
//    }

}