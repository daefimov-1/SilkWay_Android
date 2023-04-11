package com.example.silkway.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.silkway.databinding.FragmentByerBinding
import com.example.silkway.presentation.view.adapters.CatalogAdapter
import com.example.silkway.presentation.view.details.CatalogDetailsActivity
import com.example.silkway.presentation.viewmodel.MainViewModel
import androidx.lifecycle.Observer

class ByerFragment : Fragment() {

    private lateinit var binding: FragmentByerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentByerBinding.inflate(inflater, container, false)

        byerHasRequests(true)

        //List
        binding.rvRequests.layoutManager = GridLayoutManager(activity, 2)
        val adapter2 = CatalogAdapter(
            activity,
            itemClickListener = { item -> CatalogDetailsActivity.start(requireActivity(), item)}
        )
        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getRequestedCatalogList()?.observe(requireActivity(), Observer{
            adapter2.submitList(it)
        })
        binding.rvRequests.adapter = adapter2

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