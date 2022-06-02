package com.example.shortcutpractice

import android.app.PendingIntent
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
}
