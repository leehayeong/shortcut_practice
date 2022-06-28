package com.example.shortcutpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.shortcutpractice.databinding.ActivityStaticShortcutContentBinding

/**
 *  DynamicShortcutActivity.kt
 *
 *  Created by Hayeong Lee on 2022/06/02
 *  Copyright © 2022 Shinhan Bank. All rights reserved.
 */

class StaticShortcutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStaticShortcutContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_static_shortcut_content)
    }

}
