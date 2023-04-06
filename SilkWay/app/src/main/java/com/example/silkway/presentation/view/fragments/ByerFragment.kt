package com.example.silkway.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.silkway.R
import com.example.silkway.databinding.FragmentByerBinding

class ByerFragment : Fragment() {

    private lateinit var binding: FragmentByerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentByerBinding.inflate(inflater, container, false)

        byerHasRequests(false)

        return binding.getRoot()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ByerFragment()
    }

    private fun byerHasRequests(value: Boolean) {
        binding.vNoRequests.isVisible = !value
        binding.ivNoRequestsImage.isVisible = !value
        binding.tvNoRequests.isVisible = !value
        binding.btnViewCatalog.isVisible = !value
        binding.vGradient.isVisible = value
        binding.tvTextRequests.isVisible = value
        binding.nsvScrollview.isVisible = value
    }
}