package com.example.composeprac.bottom_sheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeprac.R
import com.example.composeprac.bottom_sheet.ui.theme.ComposePracTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class BottomSheetTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scaffoldState = rememberBottomSheetScaffoldState()
                    val scope = rememberCoroutineScope()

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(onClick = {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }) {
                            Text("Bottom Sheet 열기")
                        }
                    }

                    BottomSheetScaffold(
                        scaffoldState = scaffoldState,
                        sheetContent = {
                            Image(
                                painter = painterResource(id = R.drawable.test),
                                contentDescription = null
                            )
                        },
                    ) {}
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    ComposePracTheme {
        //
    }
}