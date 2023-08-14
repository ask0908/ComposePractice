package com.example.composeprac.broadcast_receiver

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeprac.broadcast_receiver.ui.theme.ComposePracTheme

class BroadcastTestActivity : ComponentActivity() {

    // 배터리 사용량이 많아져서 static receiver 사용에는 제한이 걸린다
    // static receiver를 사용한다면 registerReceiver, unregisterReceiver()는 필요없다
    private val airPlaneModeReceiver = AirPlaneModeReceiver()
    private val testReceiver = TestReceiver()

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(
            airPlaneModeReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
        registerReceiver(
            testReceiver,
            IntentFilter("테스트 액션")
        )
        setContent {
            ComposePracTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            sendBroadcast(
                                // extra 인자를 넘기거나 패키지명 명시도 가능
                                Intent("테스트 액션")
                            )
                        }) {
                            Text(text = "브로드캐스트 전송")
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneModeReceiver)
        unregisterReceiver(testReceiver)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePracTheme {
        //
    }
}