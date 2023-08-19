package com.example.composeprac.selection_components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeprac.selection_components.ui.theme.ComposePracTheme

class SelectionComponentsTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        CheckBoxes()
                        Spacer(modifier = Modifier.height(32.dp))
                        Switches()
                        Spacer(modifier = Modifier.height(32.dp))
                        RadioButtons()
                    }
                }
            }
        }
    }
}

@Composable
private fun CheckBoxes() {
    val checkBoxes = remember {
        mutableStateListOf(
            ToggleComponentInfo(
                isChecked = false,
                text = "Photos"
            ),
            ToggleComponentInfo(
                isChecked = false,
                text = "Videos"
            ),
            ToggleComponentInfo(
                isChecked = false,
                text = "Audio"
            ),
        )
    }

    var triState by remember {
        mutableStateOf(ToggleableState.Indeterminate)
    }
    val toggleTriState = {
        triState = when (triState) {
            ToggleableState.Indeterminate -> ToggleableState.On
            ToggleableState.On -> ToggleableState.Off
            else -> ToggleableState.On
        }

        checkBoxes.indices.forEach { index ->
            checkBoxes[index] = checkBoxes[index].copy(
                isChecked = (triState == ToggleableState.On)
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                toggleTriState()
            }
            .padding(end = 16.dp)
    ) {
        TriStateCheckbox(
            state = triState,
            onClick = toggleTriState
        )
        Text("File types")
    }

    checkBoxes.forEachIndexed { index, info ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 32.dp)
                .clickable {
                    checkBoxes[index] = info.copy(
                        isChecked = !info.isChecked
                    )
                }
                .padding(end = 16.dp)
        ) {
            Checkbox(
                checked = info.isChecked,
                onCheckedChange = { isChecked ->
                    checkBoxes[index] = info.copy(
                        isChecked = isChecked
                    )
                }
            )
            Text(text = info.text)
        }
    }
}

@Composable
private fun Switches() {
    var switch by remember {
        mutableStateOf(
            ToggleComponentInfo(
                isChecked = false,
                text = "다크 모드"
            )
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(switch.text)
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = switch.isChecked,
            onCheckedChange = { isChecked ->
                switch = switch.copy(isChecked = isChecked)
            },
            thumbContent = {
                Icon(
                    imageVector = if (switch.isChecked) {
                        Icons.Default.Check
                    } else {
                        Icons.Default.Close
                    },
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
private fun RadioButtons() {
    val radioButtons = remember {
        mutableStateListOf(
            ToggleComponentInfo(
                isChecked = true,
                text = "Photos"
            ),
            ToggleComponentInfo(
                isChecked = false,
                text = "Videos"
            ),
            ToggleComponentInfo(
                isChecked = false,
                text = "Audio"
            ),
        )
    }

    radioButtons.forEachIndexed { index, info ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = (it.text == info.text)
                        )
                    }
                }
                .padding(end = 16.dp)
        ) {
            RadioButton(
                selected = info.isChecked,
                onClick = {
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = (it.text == info.text)
                        )
                    }
                }
            )
            Text(text = info.text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ComposePracTheme {
        //
    }
}