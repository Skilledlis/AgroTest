package com.skilled.agrotest.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.skilled.agrotest.App
import com.skilled.agrotest.R
import com.skilled.agrotest.authentication.biometricAuthentication.BiometricAuthListener
import com.skilled.agrotest.databinding.FragmentAuthenticationBinding
import com.skilled.agrotest.viewBinding.ViewBindingHolder
import com.skilled.agrotest.viewBinding.ViewBindingHolderImpl

class AuthenticationFragment : Fragment(),
    ViewBindingHolder<FragmentAuthenticationBinding> by ViewBindingHolderImpl(),
    BiometricAuthListener, OnClickListener {

    private val viewModel: AuthenticationViewModel by viewModels {
        AuthenticationViewModelFactory(
            requireContext(),
            requireActivity(),
            this,
            (activity?.application as App).manageResource
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = initBinding(FragmentAuthenticationBinding.inflate(layoutInflater), this) {

        viewModel.authStatus.observe(viewLifecycleOwner) { haveBioAuth ->
            if (haveBioAuth) {
                fingerPrintButton.visibility = View.VISIBLE
                exitButton.visibility = View.GONE
                exitTopButton.visibility = View.VISIBLE
            } else {
                fingerPrintButton.visibility = View.GONE
                exitButton.visibility = View.VISIBLE
                exitTopButton.visibility = View.GONE
            }
        }

        viewModel.pinCode.observe(viewLifecycleOwner) { pinCode ->
            pinCode.apply {
                pinDots.text = this
                if (length == 4)
                    findNavController().navigate(R.id.action_authenticationFragment_to_contentFragment)
            }
        }

        fingerPrintButton.setOnClickListener {
            viewModel.showBioAuth()
        }

        BindingButton(this, this@AuthenticationFragment).bind()

        deleteButton.setOnClickListener {
            viewModel.deletePiCode()
        }
    }


    override fun onBiometricAuthenticationSuccess() {
        findNavController().navigate(R.id.action_authenticationFragment_to_contentFragment)
    }


    override fun onClick(view: View?) {
        viewModel.clickPinTab((view as Button).text.toString())
    }


}

class BindingButton(
    private val binding: FragmentAuthenticationBinding,
    private val clickListener: OnClickListener
) {
    fun bind() {
        binding.apply {
            button0.setOnClickListener(clickListener)
            button1.setOnClickListener(clickListener)
            button2.setOnClickListener(clickListener)
            button3.setOnClickListener(clickListener)
            button4.setOnClickListener(clickListener)
            button5.setOnClickListener(clickListener)
            button6.setOnClickListener(clickListener)
            button7.setOnClickListener(clickListener)
            button8.setOnClickListener(clickListener)
            button9.setOnClickListener(clickListener)
        }

    }
}
