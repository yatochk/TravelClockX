package com.yatochk.secure.travelclockx.map

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.yatochk.secure.travelclockx.databinding.VLocationPermissionBinding

class LocationPermissionDialog : DialogFragment() {

    companion object {
        const val TAG = "LocationPermissionDialog"
    }

    private lateinit var binding: VLocationPermissionBinding

    private val listener get() = parentFragment as? LocationGrantedListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = VLocationPermissionBinding.inflate(layoutInflater)
        binding.initView()
        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setCancelable(false)
            .create()
    }

    private fun VLocationPermissionBinding.initView() {
        buttonApprove.setOnClickListener {
            listener?.onApproveLocationGranted()
        }
        buttonCancel.setOnClickListener {
            listener?.onDismissLocationGranted()
        }
    }

    interface LocationGrantedListener {
        fun onApproveLocationGranted()
        fun onDismissLocationGranted()
    }

}