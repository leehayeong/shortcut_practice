package com.example.shortcutpractice

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.databinding.DataBindingUtil
import com.example.shortcutpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setPinnedShortcut()

        binding.dynamicShortcutBtn.setOnClickListener {
            setDynamicShortcut()    // 여러번 클릭해도 같은 숏컷은 한개만 생성됨
        }
    }

    private fun setPinnedShortcut() {
        binding.pinnedShortcutBtn.setOnClickListener {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                val shortcutManager = getSystemService(ShortcutManager::class.java)
//
//                if (shortcutManager!!.isRequestPinShortcutSupported) {
//                    val pinShortcutInfo =
//                        ShortcutInfo.Builder(this, "my-shortcut")
//                            .setShortLabel("pinned shortcut")
//                            .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("pinned://deeplink")))
//                            .build()
//
//                    val pinnedShortcutCallbackIntent =
//                        shortcutManager.createShortcutResultIntent(pinShortcutInfo)
//
//                    val successCallback = PendingIntent.getBroadcast(
//                        this,
//                        0,
//                        pinnedShortcutCallbackIntent,
//                        0)
//
//                    shortcutManager.requestPinShortcut(pinShortcutInfo, successCallback.intentSender)
//                }
//            } else {
//                // asis code
//                val shortcutIntent = Intent()
//                shortcutIntent.action = Intent.ACTION_VIEW
//                shortcutIntent.addCategory(Intent.CATEGORY_DEFAULT)
//                shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//                val shortcut = Intent("com.android.launcher.action.INSTALL_SHORTCUT")
//                shortcut.putExtra("duplicate", false)
//                shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent)
//                shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "name")
//                shortcutIntent.data = Uri.parse("pinned://deeplink")
//                this.sendBroadcast(shortcut)
//            }

            val shortcutInfo: ShortcutInfoCompat =
                ShortcutInfoCompat.Builder(this, "id")
                    .setShortLabel("pinned shortcut")
                    .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("pinned://deeplink")))
                    .build()

            ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null)
        }
    }

    private fun setDynamicShortcut() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val context = applicationContext
            val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)
            val shortcut = ShortcutInfo.Builder(context, "shortcutId1")
                .setShortLabel("dynamic")
                .setLongLabel("dynamic - longLabel")
                .setDisabledMessage("This shortcut is disabled")
                .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("dynamic://deeplink")))
                .build()

            shortcutManager.dynamicShortcuts = listOf(shortcut)
        } else {
            TODO("VERSION.SDK_INT < N_MR1")
        }
    }
}
