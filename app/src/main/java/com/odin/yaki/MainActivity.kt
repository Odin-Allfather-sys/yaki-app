package com.odin.yaki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.odin.yaki.ui.SetupScreen
import com.odin.yaki.ui.HomeScreen
import com.odin.yaki.ui.theme.YakiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YakiTheme {
                var vehicleSaved by remember { mutableStateOf(false) }
                if (!vehicleSaved) {
                    SetupScreen(onDone = { vehicleSaved = true })
                } else {
                    HomeScreen()
                }
            }
        }
    }
}
