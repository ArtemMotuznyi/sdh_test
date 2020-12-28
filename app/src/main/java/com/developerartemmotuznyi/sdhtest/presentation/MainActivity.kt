package com.developerartemmotuznyi.sdhtest.presentation

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.developerartemmotuznyi.sdhtest.R
import com.developerartemmotuznyi.sdhtest.databinding.ActivityMainBinding
import com.developerartemmotuznyi.sdhtest.presentation.base.ViewBindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    override val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBarWithNavController(findNavController(R.id.main_host_fragment))
    }
}