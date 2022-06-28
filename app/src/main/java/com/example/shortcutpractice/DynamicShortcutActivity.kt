package com.example.shortcutpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.shortcutpractice.databinding.ActivityDynamicShortcutContentBinding

/**
 *  DynamicShortcutActivity.kt
 *
 *  Created by Hayeong Lee on 2022/06/02
 *  Copyright © 2022 Shinhan Bank. All rights reserved.
 */

class DynamicShortcutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDynamicShortcutContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dynamic_shortcut_content)
    }

}
