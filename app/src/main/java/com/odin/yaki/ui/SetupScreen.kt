package com.odin.yaki.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odin.yaki.ui.theme.Acc
import com.odin.yaki.ui.theme.Acc2
import com.odin.yaki.ui.theme.BarlowSC
import com.odin.yaki.ui.theme.Bg
import com.odin.yaki.ui.theme.Dim
import com.odin.yaki.ui.theme.Ink
import com.odin.yaki.ui.theme.Inset
import com.odin.yaki.ui.theme.Line
import com.odin.yaki.ui.theme.Line2
import com.odin.yaki.ui.theme.Mut
import com.odin.yaki.ui.theme.Ok
import com.odin.yaki.ui.theme.Panel
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun YakiField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            label.uppercase(),
            style = TextStyle(
                fontFamily = BarlowSC,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.5.sp,
                letterSpacing = 1.0.sp
            ),
            color = Mut,
            modifier = Modifier.padding(start = 3.dp, bottom = 6.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(placeholder, color = Dim, style = MaterialTheme.typography.bodyLarge)
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Acc,
                unfocusedBorderColor = Line2,
                focusedContainerColor = Inset,
                unfocusedContainerColor = Inset,
                cursorColor = Acc,
                focusedTextColor = Ink,
                unfocusedTextColor = Ink,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 44.dp)
        )
    }
}

@Composable
fun SetupScreen(onDone: () -> Unit) {
    var year by remember { mutableStateOf("") }
    var make by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var engine by remember { mutableStateOf("") }
    var odo by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var saved by remember { mutableStateOf(false) }

    if (saved) {
        SetupSuccess(onDone)
        return
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Bg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(34.dp))
        BrandHeader()
        Spacer(Modifier.height(18.dp))
        SubHeader()
        Spacer(Modifier.height(14.dp))

        Surface(
            shape = RoundedCornerShape(18.dp),
            color = Panel,
            border = androidx.compose.foundation.BorderStroke(1.dp, Line),
            shadowElevation = 8.dp
        ) {
            Column(Modifier.padding(12.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    YakiField(
                        label = "Year",
                        value = year,
                        onValueChange = { year = it.filter { c -> c.isDigit() }.take(4) },
                        placeholder = "2008",
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier.weight(1f),
                    )
                    YakiField(
                        label = "Make",
                        value = make,
                        onValueChange = { make = it },
                        placeholder = "Honda",
                        modifier = Modifier.weight(0.74f)
                    )
                }
                YakiField(
                    label = "Model",
                    value = model,
                    onValueChange = { model = it },
                    placeholder = "Ridgeline RTL",
                    modifier = Modifier.weight(1f)
                )
                YakiField(
                    label = "Engine",
                    value = engine,
                    onValueChange = { engine = it },
                    placeholder = "3.5L V6 (J35)",
                    modifier = Modifier.weight(1f)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    YakiField(
                        label = "Current odometer",
                        value = odo,
                        onValueChange = { odo = it.filter { c -> c.isDigit() || c == ',' }.take(8) },
                        placeholder = "197,415",
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier.weight(1f),
                    )
                    YakiField(
                        label = "Nickname (optional)",
                        value = nickname,
                        onValueChange = { nickname = it },
                        placeholder = "Ridge",
                        modifier = Modifier.weight(0.74f)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color.Transparent,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Acc.copy(alpha = 0.28f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp, bottom = 12.dp)
                ) {
                    Row(
                        Modifier
                            .background(Brush.linearGradient(listOf(Acc.copy(alpha = 0.09f), Acc.copy(alpha = 0.03f))))
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Starting mileage is optional", style = MaterialTheme.typography.bodyMedium, color = Mut)
                        Text("Used for MPG math", style = MaterialTheme.typography.labelMedium, color = Acc)
                    }
                }

                Button(
                    onClick = { saved = true },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color(0xFF1A0E02)),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .background(
                            Brush.linearGradient(listOf(Acc, Acc2)),
                            RoundedCornerShape(15.dp)
                        )
                ) {
                    Text("SAVE VEHICLE", style = MaterialTheme.typography.titleMedium, letterSpacing = 0.5.sp)
                }
            }
        }

        Spacer(Modifier.height(6.dp))
        TextButton(onClick = onDone, Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                "I'll add a vehicle later",
                style = MaterialTheme.typography.bodyMedium,
                color = Dim
            )
        }
        Spacer(Modifier.height(18.dp))
    }
}

@Composable
fun BrandHeader() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = Color.Transparent,
            modifier = Modifier
                .size(44.dp)
                .background(Brush.linearGradient(listOf(Acc, Color(0xFFFF6D00))), RoundedCornerShape(14.dp))
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    "Y",
                    style = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp),
                    color = Color(0xFF1A0E02)
                )
            }
        }
        Column {
            Text(
                "YAKI",
                style = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp, letterSpacing = 1.sp),
                color = Ink
            )
            Text("Fuel & trip tracking", style = MaterialTheme.typography.bodySmall, color = Mut)
        }
    }
}

@Composable
fun SubHeader() {
    Column(Modifier.padding(horizontal = 6.dp)) {
        Text("Add your first vehicle", style = MaterialTheme.typography.headlineSmall, color = Ink)
        Text(
            "Yaki tracks fill-ups, efficiency, and costs per vehicle. You can add more later.",
            style = MaterialTheme.typography.bodyMedium,
            color = Mut,
            lineHeight = 19.sp,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Composable
fun SetupSuccess(onDone: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Bg)
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Panel,
            border = androidx.compose.foundation.BorderStroke(1.dp, Line),
            shadowElevation = 14.dp
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = Ok.copy(alpha = 0.1f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Ok.copy(alpha = 0.35f)),
                    modifier = Modifier.size(46.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("✓", color = Ok, style = MaterialTheme.typography.titleLarge)
                    }
                }
                Spacer(Modifier.height(12.dp))
                Text("Ridgeline added", style = MaterialTheme.typography.headlineSmall, color = Ink)
                Text(
                    "Your first vehicle is set up. Log a fill-up whenever you fill the tank to start tracking efficiency.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Mut,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 6.dp)
                )
                Spacer(Modifier.height(16.dp))
                HorizontalDivider(color = Line2, thickness = 1.dp)
                Spacer(Modifier.height(14.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Odometer", style = MaterialTheme.typography.bodyMedium, color = Mut)
                    Text("197,415 mi", style = MaterialTheme.typography.labelMedium, color = Ink)
                }
                Spacer(Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Fuel tracking", style = MaterialTheme.typography.bodyMedium, color = Mut)
                    Text("Ready", style = MaterialTheme.typography.labelMedium, color = Ink)
                }
                Spacer(Modifier.height(18.dp))
                Button(
                    onClick = onDone,
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color(0xFF1A0E02)),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .background(Brush.linearGradient(listOf(Acc, Acc2)), RoundedCornerShape(15.dp))
                ) {
                    Text("Start logging", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
