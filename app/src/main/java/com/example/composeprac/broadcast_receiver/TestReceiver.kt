package com.example.composeprac.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TestReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "테스트 액션") {
            println("## 테스트 인텐트 받음!")
        }
    }
}