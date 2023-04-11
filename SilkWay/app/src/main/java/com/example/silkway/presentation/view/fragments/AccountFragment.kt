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
import com.example.silkway.presentation.view.LoginActivity
import com.example.silkway.presentation.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class AccountFragment : Fragment() {
    private lateinit var nameField: TextView
    private lateinit var emailField: TextView
    private lateinit var logOutButton: TextView
    private val loginStorage by inject<LoginStorage>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        nameField = view.findViewById(R.id.tv_name)
        emailField = view.findViewById(R.id.tv_email)
        logOutButton = view.findViewById(R.id.logOut)
        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        logOutButton.setOnClickListener {
            loginStorage.clearAll()
            mainViewModel.deleteCatalogInfo()
            LoginActivity.start(requireActivity())
            requireActivity().finish()
        }
        val user = loginStorage.getUserInfo()
        nameField.text = user.name
        emailField.text = user.email
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = AccountFragment()
    }
}