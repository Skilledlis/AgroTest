package com.skilled.agrotest.main

import android.content.Context
import androidx.annotation.StringRes

/**
 * @created 18.11.2022
 * @author SkilledLis
 */
interface ManageResources {
    fun string(@StringRes id: Int): String

    class Base(private val context: Context): ManageResources {
        override fun string(id: Int): String = context.getString(id)
    }
}