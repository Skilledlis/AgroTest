package com.skilled.agrotest.viewBinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding

/**
 * @created 19.11.2022
 * @author SkilledLis
 */
interface ViewBindingHolder<T : ViewBinding> {
    val binding: T?

    fun initBinding(binding: T, fragment: Fragment, onBound: (T.() -> Unit)?): View

    fun requireBinding(block: (T.() -> Unit)? = null): T
}


class ViewBindingHolderImpl<T : ViewBinding> : ViewBindingHolder<T>, DefaultLifecycleObserver {

    override var binding: T? = null
    var lifecycle: Lifecycle? = null

    private lateinit var fragmentName: String

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        lifecycle?.removeObserver(this) // not mandatory, but preferred
        lifecycle = null
        binding = null
    }


    override fun requireBinding(block: (T.() -> Unit)?) = binding?.apply { block?.invoke(this) }
        ?: throw IllegalStateException("Accessing binding outside of Fragment lifecycle: $fragmentName")

    override fun initBinding(binding: T, fragment: Fragment, onBound: (T.() -> Unit)?): View {
        this.binding = binding
        lifecycle = fragment.viewLifecycleOwner.lifecycle
        lifecycle?.addObserver(this)
        fragmentName = fragment::class.simpleName ?: "N/A"
        onBound?.invoke(binding)
        return binding.root
    }
}