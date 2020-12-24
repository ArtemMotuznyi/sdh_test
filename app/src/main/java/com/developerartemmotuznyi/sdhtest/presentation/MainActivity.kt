package com.developerartemmotuznyi.sdhtest.presentation

import by.kirich1409.viewbindingdelegate.viewBinding
import com.developerartemmotuznyi.sdhtest.databinding.ActivityMainBinding
import com.developerartemmotuznyi.sdhtest.presentation.base.ViewBindingActivity

class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    override val binding: ActivityMainBinding by viewBinding()
}