package com.example.pmsdemo

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityEvent.TYPE_VIEW_CLICKED
import android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import java.nio.file.Path

/**
 * 浙江集商优选电子商务有限公司
 * @author zenglw
 * @date   2/27/21 3:54 PM
 */
class MyAccessbilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            Log.e("MyAccessbilityService","onAccessibilityEvent evnet : $event \n windows : $windows \n rootInWindow : $rootInActiveWindow")
//            rootInActiveWindow.performAction(AccessibilityNodeInfo.ACTION_CLICK, null)
//            performGlobalAction(GLOBAL_ACTION_BACK)
//            performGlobalAction(GLOBAL_ACTION_RECENTS)
//            Log.e("MyAccessbilityService", "onAccessibilityEvent $event ${event.parcelableData} \n 1111111111 text === ${event.text} ${event.text.any { it.contains("通知栏") }}")
            if (event.eventType == TYPE_WINDOW_STATE_CHANGED && !event.text.any { it.contains("通知栏") }) {
                val node = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.example.testedit:id/constraint")
                Log.e("MyAccessbilityService","onAccessibilityEvent node == \n\n  $node ")
                node[0].performAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD)
//                node[0].performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, Bundle().apply {
//                    putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "啊啊发的说")            }
//                )
//                node[0].performAction(AccessibilityNodeInfo.ACTION_SET_TEXT)
//                node[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)

//                Log.e("MyAccessbilityService", "if 进来了")
//                findFocus(AccessibilityNodeInfo.FOCUS_INPUT)?.run {
//                    performAction(AccessibilityNodeInfo.ACTION_SET_TEXT)
//                    (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newPlainText("复制", "sdfgsd 复制内容"))
//                    performAction(AccessibilityNodeInfo.FOCUS_INPUT)
//                    performAction(AccessibilityNodeInfo.ACTION_PASTE)
//                    performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
//                    recycle()
//                }

//                performGlobalAction(GLOBAL_ACTION_NOTIFICATIONS)

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

//                    rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.example.testedit:id/seekbar")?.get(0)?.run {
//
//                        Log.e("MyAccessbilityService","onAccessibilityEvent 找到seekbar")
//                        performAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS.id, Bundle().apply {
//                            putFloat(AccessibilityNodeInfo.ACTION_ARGUMENT_PROGRESS_VALUE, 50f)
//                        })
//
//                    }
//                    findConstraintLayout(rootInActiveWindow)
//                    dispatchGesture(GestureDescription.Builder().addStroke(GestureDescription.StrokeDescription(
//                            android.graphics.Path().apply {
//
//                                moveTo(0f, 85f)
//                            },
//                            0, 0
//                    )).build(), object : GestureResultCallback() {
//
//                        override fun onCancelled(gestureDescription: GestureDescription?) {
//                            Log.e("MyAccessbilityService", "onCancelled ")
//                            super.onCancelled(gestureDescription)
//                        }
//
//                        override fun onCompleted(gestureDescription: GestureDescription?) {
//                            Log.e("MyAccessbilityService", "onCompleted ")
//                            super.onCompleted(gestureDescription)
//                        }
//                    }, null)
                }
            }
//            node[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
//            Log.e("MyAccessbilityService","onAccessibilityEvent $node  ${event.eventType}")
        }
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            findFocus(AccessibilityNodeInfo.FOCUS_INPUT)?.run {
////                this.text = "阿斯顿发卡死李开复 "
//                performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, Bundle().apply {
//                    putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "sad看风景暗室逢灯 ")
//                })
//            }
//
//            Log.e("MyAccessbilityService","onAccessibilityEvent $text ${text.text}")
//
//        }

//        if (event.eventType == TYPE_VIEW_CLICKED) {
//            val contentDescription = event.contentDescription
//            Toast.makeText(this, "content = $contentDescription", Toast.LENGTH_SHORT).show()
//        }

//        if (event.eventType == TYPE_WINDOW_STATE_CHANGED) {
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                Log.e("MyAccessbilityService","onAccessibilityEvent $windows")
//            }
//        }
    }

    private fun findConstraintLayout(rootInActiveWindow: AccessibilityNodeInfo?) {
        Log.e("MyAccessbilityService","findConstraintLayout ${rootInActiveWindow?.className} ${rootInActiveWindow?.contentDescription}  \n\n")
        rootInActiveWindow ?: return
        if (rootInActiveWindow?.className == "androidx.constraintlayout.widget.ConstraintLayout  \n\n") {

            Log.e("MyAccessbilityService","findConstraintLayout 找到了 \n$rootInActiveWindow  \n\n ")
            return
        }
        for (i in 0 until (rootInActiveWindow?.childCount ?: 0)) {
            rootInActiveWindow?.getChild(i)?.let {
                findConstraintLayout(it)
            }
        }
    }

    override fun onInterrupt() {
        Log.e("MyAccessbilityService", "onInterrupt ")
        mService = null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("MyAccessbilityService", "onStartCommand ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onServiceConnected() {
        Log.e("MyAccessbilityService", "onServiceConnected ")
        Toast.makeText(this, "红包", Toast.LENGTH_SHORT).show()
        mService = this
        super.onServiceConnected()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.e("MyAccessbilityService", "onStart ")
    }

    override fun onDestroy() {
        super.onDestroy()
        mService = null
        Log.e("MyAccessbilityService", "onDestroy zhongudan ")
    }

    override fun stopService(name: Intent?): Boolean {
        Log.e("MyAccessbilityService", "stopService ")
        return super.stopService(name)
    }

    companion object {
        private var mService: MyAccessbilityService? = null
        fun isStart(): Boolean {

            return mService != null
        }
    }
}