package com.developerartemmotuznyi.sdhtest.presentation.extensions

import android.content.Context
import android.widget.Toast
import com.developerartemmotuznyi.sdhtest.R

fun noInternetConnection(context: Context) {
    Toast.makeText(context, R.string.no_connect, Toast.LENGTH_SHORT).show()
}