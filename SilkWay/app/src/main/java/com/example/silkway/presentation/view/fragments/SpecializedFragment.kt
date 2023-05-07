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
import com.example.silkway.data.storage.LoginStorage
import org.koin.android.ext.android.inject

class SpecializedFragment : Fragment() {

    private lateinit var binding: FragmentByerBinding
    private val loginStorage by inject<LoginStorage>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentByerBinding.inflate(inflater, container, false)

        //List
        binding.rvRequests.layoutManager = GridLayoutManager(activity, 2)
        val adapter = CatalogAdapter(
            activity,
            itemClickListener = { item -> CatalogDetailsActivity.start(requireActivity(), item)},
            loginStorage,
            true
        )
        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        if(loginStorage.isByer()) {
            mainViewModel.getRequestedCatalogList()?.observe(requireActivity(), Observer{
                adapter.submitList(it)
                if (it.isEmpty()) {
                    byerHasRequests(false)
                } else {
                    byerHasRequests(true)
                }
            })
        } else {
            mainViewModel.getMineCatalogList()?.observe(requireActivity(), Observer{
                adapter.submitList(it)
                if (it.isEmpty()) {
                    supplierHasProducts(false)
                } else {
                    supplierHasProducts(true)
                }
            })
        }

        binding.rvRequests.adapter = adapter

        return binding.getRoot()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = SpecializedFragment()
    }

    private fun byerHasRequests(value: Boolean) {
        binding.tvTitle.text = "Requests"
        binding.vNoRequests.isVisible = !value
        binding.ivNoRequestsImage.isVisible = !value
        binding.tvNoRequests.isVisible = !value
        binding.btnViewCatalog.isVisible = !value
        binding.vGradient.isVisible = value
        binding.tvTitle.isVisible = value
        binding.nsvScrollview.isVisible = value
    }

    private fun supplierHasProducts(value: Boolean) {
        binding.tvTitle.text = "My current deals"
        binding.vNoRequests.isVisible = !value
        binding.ivNoRequestsImage.isVisible = !value
        binding.tvNoRequests.isVisible = !value
        binding.tvNoRequests.text = "You have no products"
        binding.btnViewCatalog.isVisible = !value
        binding.btnViewCatalog.text = "Create delivery deal"
        binding.btnViewCatalog.setOnClickListener {
            //TODO start activity for adding catalog item
        }
        binding.vGradient.isVisible = value
        binding.tvTitle.isVisible = value
        binding.btnCreateDeal.isVisible = value
        binding.btnCreateDeal.setOnClickListener {
            //TODO start activity for adding catalog item
        }
        binding.nsvScrollview.isVisible = value
    }
}