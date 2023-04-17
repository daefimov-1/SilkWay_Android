package com.example.silkway.presentation.view.fragments.dialogs

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.silkway.R
import com.example.silkway.databinding.ByerBottomSheetBinding
import com.example.silkway.presentation.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ByerBottomSheetDialog(
    private val item_id: Int,
    private val needed_amount: Int,
    private val youHaveRequested: Int
): BottomSheetDialogFragment() {
    lateinit var binding: ByerBottomSheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ByerBottomSheetBinding.bind(inflater.inflate(R.layout.byer_bottom_sheet, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        if (youHaveRequested > 0) {
            binding.textInputEdittextQuantity1.setText(youHaveRequested.toString())
            binding.ibDelete.visibility = View.VISIBLE

            binding.ibDelete.setOnClickListener {
                mainViewModel.updateCatalogItemYouRequested(0, item_id)
                dialog?.dismiss()
            }
        }

        binding.tvAmountNeeded.text = needed_amount.toString()
        binding.buttonA.setOnClickListener {


            val youRequested = binding.textInputEdittextQuantity1.text.toString().toInt()
            if(youRequested > 0) {
                mainViewModel.updateCatalogItemYouRequested(youRequested, item_id)
                dialog?.dismiss()
            } else {
                binding.tvLeftToComplete.visibility = View.GONE
                binding.tvAmountNeeded.visibility = View.GONE
                binding.tvWarningText.visibility = View.VISIBLE
                binding.tvWarningText.text = "Quantity you request must be greater than 0"
            }
        }
    }
}