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
import androidx.core.graphics.drawable.IconCompat
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

        // Dynamic shortcut 은 숏컷ID 를 알고있다면 앱 실행중에 정보를 변경할 수 있음
        binding.dynamicRemoveBtn.setOnClickListener {
            removeDynamicShortcut("6")
        }

        binding.dynamicChangeLabelBtn.setOnClickListener {
            updateDynamicShortcut()
        }

        // 다이나믹 숏컷 추가할 때 개수 테스트
        binding.addBtn1.setOnClickListener {
            addDynamicShortcut("1")
        }
        binding.addBtn2.setOnClickListener {
            addDynamicShortcut("2")
        }
        binding.addBtn3.setOnClickListener {
            addDynamicShortcut("3")
        }
        binding.addBtn4.setOnClickListener {
            addDynamicShortcut("4")
        }
        binding.addBtn5.setOnClickListener {
            addDynamicShortcut("5")
        }
        binding.addBtn6.setOnClickListener {
            addDynamicShortcut("6")
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
            val shortcut = ShortcutInfo.Builder(context, "dynamicShortcutId")
                .setShortLabel("dynamic")
                .setLongLabel("dynamic - longLabel")
                .setDisabledMessage("This shortcut is disabled")
                .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("dynamic://deeplink")))
                .build()

            shortcutManager.dynamicShortcuts = listOf(shortcut)     // 리스트자체를 바꿔주는거라 기존거 다 사라지고 새로운 숏컷리스트로 덮어씌워짐
        } else {
        }
    }

    private fun removeDynamicShortcut(shortcutId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val context = applicationContext
            val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)
            shortcutManager.removeDynamicShortcuts(listOf(shortcutId))  // 특정 아이디를 가진 숏컷만 지우기
//            shortcutManager.removeAllDynamicShortcuts()               // 전체 다이나믹 숏컷 지우기
        } else {
        }
    }

    private fun updateDynamicShortcut() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val context = applicationContext
            val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)
            val shortcut = ShortcutInfo.Builder(context, "dynamicShortcutId")
                .setShortLabel("dynamic2222")
                .setLongLabel("dynamic - longLabel2222")
                .setDisabledMessage("This shortcut is disabled")
                .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("dynamic://deeplink")))
                .build()

            shortcutManager.updateShortcuts(listOf(shortcut))   // 아이디로 숏컷을 찾아 변경하고 업데이트
        } else {
        }
    }

    /**
     * ShortcutManagerCompat > pushDynamicShortcut 버전별로 보일러플레이트 코드 없애고 자동분기
     * 가장 먼저 추가한게 가장 위에 뜨고, 가장 마지막에 추가한게 가장 아래에 뜸
     * 디바이스에서 보여줄 수 있는 높이를 초과하면 가장 먼저 추가한 숏컷이 삭제되고, 가장 최근에 추가한게 가장 아래에 뜸
     */
    private fun addDynamicShortcut(shortcutId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            val context = applicationContext
            val shortcut = ShortcutInfoCompat.Builder(context, shortcutId)
                .setShortLabel(shortcutId)
                .setLongLabel(shortcutId)
                .setIcon(IconCompat.createWithResource(context, R.drawable.ic_launcher_background))
                .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("dynamic://deeplink")))
                .build()

            ShortcutManagerCompat.pushDynamicShortcut(context, shortcut)
        } else {
        }
    }
}
