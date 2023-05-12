package com.example.silkway.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.silkway.R
import com.example.silkway.data.storage.LoginStorage
import com.example.silkway.databinding.FragmentAccountBinding
import com.example.silkway.databinding.FragmentByerBinding
import com.example.silkway.presentation.view.GetHelpActivity
import com.example.silkway.presentation.view.LoginActivity
import com.example.silkway.presentation.view.WishlistActivity
import com.example.silkway.presentation.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class AccountFragment : Fragment() {
    private val loginStorage by inject<LoginStorage>()
    private lateinit var binding: FragmentAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.logOut.setOnClickListener {
            loginStorage.clearAll()
            mainViewModel.deleteCatalogInfo()
            LoginActivity.start(requireActivity())
            requireActivity().finish()
        }
        binding.myWishList.setOnClickListener {
            WishlistActivity.start(requireActivity())
        }
        binding.tvGetHelp.setOnClickListener {
            GetHelpActivity.start(requireActivity())
        }
        val user = loginStorage.getUserInfo()
        binding.tvName.text = user.name
        binding.tvEmail.text = user.email
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = AccountFragment()
    }
}