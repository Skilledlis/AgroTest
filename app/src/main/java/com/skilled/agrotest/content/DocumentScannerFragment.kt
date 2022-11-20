package com.skilled.agrotest.content

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.skilled.agrotest.databinding.FragmentDocumentScannerBinding
import com.skilled.agrotest.viewBinding.ViewBindingHolder
import com.skilled.agrotest.viewBinding.ViewBindingHolderImpl
import io.scanbot.sdk.ui.view.mrz.MRZScannerActivity
import io.scanbot.sdk.ui.view.mrz.configuration.MRZScannerConfiguration


class DocumentScannerFragment : Fragment(),
    ViewBindingHolder<FragmentDocumentScannerBinding> by ViewBindingHolderImpl() {


    private val mrzCameraConfiguration = MRZScannerConfiguration()
    private val permission = Manifest.permission.CAMERA

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = initBinding(FragmentDocumentScannerBinding.inflate(layoutInflater), this) {

        scanDocumentButton.setOnClickListener {
            requestPermissionLauncher.launch(permission)
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted)
                documentScannerResult.launch(mrzCameraConfiguration)
            else
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(permission), 1
                )
        }

    private val documentScannerResult: ActivityResultLauncher<MRZScannerConfiguration> =
        registerForActivityResult(MRZScannerActivity.ResultContract()) { result ->
            if (result.resultOk)
                Log.d("DOC_SCAN", result.result?.firstNameField().toString())
        }
}