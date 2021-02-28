package com.example.pmsdemo

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityEvent.TYPE_VIEW_CLICKED
import android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast

/**
 * 浙江集商优选电子商务有限公司
 * @author zenglw
 * @date   2/27/21 3:54 PM
 */
class MyAccessbilityService  : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            Log.e("MyAccessbilityService","onAccessibilityEvent evnet : $event \n windows : $windows \n rootInWindow : $rootInActiveWindow")
//            rootInActiveWindow.performAction(AccessibilityNodeInfo.ACTION_CLICK, null)
//            performGlobalAction(GLOBAL_ACTION_BACK)
//            performGlobalAction(GLOBAL_ACTION_RECENTS)
            if (event.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED || event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                val node = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.example.testedit:id/tv")
                Log.e("MyAccessbilityService","onAccessibilityEvent $node ${node.size} ${node[0]} ${node[0].text}")
//                node[0].performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, Bundle().apply {
//                    putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "啊啊发的说")            }
//                )


                node[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
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

    override fun onInterrupt() {
        Log.e("MyAccessbilityService","onInterrupt ")
        mService = null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("MyAccessbilityService","onStartCommand ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onServiceConnected() {
        Log.e("MyAccessbilityService","onServiceConnected ")
        Toast.makeText(this, "红包", Toast.LENGTH_SHORT).show()
        mService = this
        super.onServiceConnected()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.e("MyAccessbilityService","onStart ")
    }

    override fun onDestroy() {
        super.onDestroy()
        mService = null
        Log.e("MyAccessbilityService","onDestroy zhongudan ")
    }

    override fun stopService(name: Intent?): Boolean {
        Log.e("MyAccessbilityService","stopService ")
        return super.stopService(name)
    }

    companion object {
        private var mService: MyAccessbilityService? = null
        fun isStart(): Boolean {

            return mService != null
        }
    }
}