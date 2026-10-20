package com.odin.yaki.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odin.yaki.ui.theme.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.TextStyle as ComposeTextStyle

data class TankEntry(val station: String, val date: String, val odometer: String, val gallons: String, val octane: Int, val mpg: Double, val price: Double)

@Composable
fun HomeScreen() {
    var odo by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("2026-10-19") }
    var gallons by remember { mutableStateOf("") }
    var total by remember { mutableStateOf("") }
    var octane by remember { mutableStateOf("") }
    var pricePerGal by remember { mutableStateOf("") }
    var station by remember { mutableStateOf("") }
    var toast by remember { mutableStateOf<String?>(null) }

    val recent = listOf(
        TankEntry("Costco — Clinton", "Oct 12", "197,019 mi", "16.4 gal", 87, 19.4, 56.09),
        TankEntry("Sinclair — Syracuse", "Sep 28", "196,701 mi", "15.4 gal", 87, 18.9, 52.05),
        TankEntry("Chevron — Kaysville", "Sep 14", "196,395 mi", "16.2 gal", 87, 18.6, 53.62),
    )

    val previewMpg = run {
        val o = odo.replace(",", "").toDoubleOrNull()
        val g = gallons.toDoubleOrNull()
        if (o != null && g != null && o > 196701 && g > 0) "%.1f".format((o - 196701) / g) else "—"
    }

    Box(Modifier.fillMaxSize().background(Bg)) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(10.dp))
            AppBar()
            Spacer(Modifier.height(2.dp))
            HeroSection()
            Spacer(Modifier.height(12.dp))
            StatRow()
            Spacer(Modifier.height(14.dp))
            SectionCap(title = "New Fill-Up", subtitle = "Full tank required for accurate MPG")
            FormCard(
                odo = odo, onOdo = { odo = it },
                date = date, onDate = { date = it },
                gallons = gallons, onGallons = { gallons = it },
                total = total, onTotal = { total = it },
                octane = octane, onOctane = { octane = it },
                pricePerGal = pricePerGal, onPrice = { pricePerGal = it },
                station = station, onStation = { station = it },
                previewMpg = previewMpg,
                onSave = {
                    toast = "Fill-up saved · ${previewMpg} mpg"
                }
            )
            SectionCap(title = "Recent Tanks", subtitle = "18.7 MPG average · 4 fill-ups", padTop = 6.dp, padBottom = 8.dp)
            recent.forEach { TankEntry(it) }
            Spacer(Modifier.height(16.dp))
            Text(
                "Entries can also be added through your assistant",
                style = MaterialTheme.typography.bodySmall,
                color = Dim,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp)
            )
        }

        if (toast != null) {
            Surface(
                shape = RoundedCornerShape(50),
                color = Panel2,
                shadowElevation = 14.dp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 26.dp)
            ) {
                Row(Modifier.padding(horizontal = 18.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Fill-up saved · ", style = MaterialTheme.typography.bodyMedium, color = Ink)
                    Text(toast!!.substringAfter("· "), style = MaterialTheme.typography.titleMedium, color = Acc)
                }
            }
            LaunchedEffect(toast) {
                kotlinx.coroutines.delay(2200)
                toast = null
            }
        }
    }
}

@Composable
fun AppBar() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 2.dp)) {
        Surface(
            shape = RoundedCornerShape(11.dp),
            color = Color.Transparent,
            modifier = Modifier
                .size(34.dp)
                .background(Brush.linearGradient(listOf(Acc, Color(0xFFFF6D00))), RoundedCornerShape(11.dp))
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("Y", style = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp), color = Color(0xFF1A0E02))
            }
        }
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text("RIDGELINE", style = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.Bold, fontSize = 18.sp, letterSpacing = 0.5.sp), color = Ink)
            Text("2008 Honda Ridgeline RTL · J35", style = MaterialTheme.typography.bodySmall, color = Mut)
        }
        Surface(
            shape = RoundedCornerShape(50),
            color = Panel2,
            border = androidx.compose.foundation.BorderStroke(1.dp, Line)
        ) {
            Row(
                Modifier.padding(horizontal = 11.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(Modifier.size(6.dp).background(Ok, CircleShape))
                Text("Synced", style = MaterialTheme.typography.labelSmall, color = Mut, letterSpacing = 0.6.sp)
            }
        }
    }
}

@Composable
fun HeroSection() {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color.Transparent,
        border = androidx.compose.foundation.BorderStroke(1.dp, Line2),
        shadowElevation = 14.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            Modifier
                .background(Brush.linearGradient(listOf(Color(0xFF1B1B22), Color(0xFF131318))))
        ) {
            Column(Modifier.padding(horizontal = 18.dp).padding(top = 14.dp)) {
                Text("LAST TANK", style = MaterialTheme.typography.labelSmall, color = Mut, letterSpacing = 1.4.sp)
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                    Text("19.4", style = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.ExtraBold, fontSize = 42.sp, letterSpacing = (-0.5).sp), color = Acc)
                    Text("mpg", style = MaterialTheme.typography.titleMedium, color = Dim, modifier = Modifier.padding(bottom = 6.dp))
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Ok.copy(alpha = 0.1f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Ok.copy(alpha = 0.22f))
                    ) {
                        Text(
                            "+0.5 vs average",
                            style = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.SemiBold, fontSize = 12.sp),
                            color = Ok,
                            modifier = Modifier.padding(horizontal = 9.dp, vertical = 3.dp)
                        )
                    }
                }
                MpgSparkline()
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    listOf("AUG 30", "SEP 14", "SEP 28", "OCT 12").forEach {
                        Text(it, style = MaterialTheme.typography.labelSmall, color = Dim, letterSpacing = 0.4.sp)
                    }
                }
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun MpgSparkline() {
    val pts = listOf(33f, 50f, 37f, 16f)
    val lineColor = Brush.horizontalGradient(listOf(Color(0xFFB45812), Acc2))
    Canvas(Modifier.fillMaxWidth().height(52.dp).padding(top = 8.dp)) {
        val w = size.width
        val h = size.height
        val xStep = (w - 20f) / (pts.size - 1)
        // dashed average line at y = 45.5 scaled
        drawLine(
            color = Line2,
            start = Offset(10f, 45.5f),
            end = Offset(w - 10f, 45.5f),
            strokeWidth = 1f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(3f, 4f))
        )
        val path = Path()
        val ptsAbs = pts.mapIndexed { i, y -> Offset(10f + i * xStep, y) }
        path.moveTo(ptsAbs[0].x, ptsAbs[0].y)
        ptsAbs.drop(1).forEach { path.lineTo(it.x, it.y) }
        drawPath(path, brush = lineColor, style = Stroke(width = 2.5f, cap = StrokeCap.Round, join = StrokeJoin.Round))
        ptsAbs.forEachIndexed { i, p ->
            drawCircle(
                color = if (i == pts.lastIndex) Acc2 else Color(0xFF3A3A42),
                radius = if (i == pts.lastIndex) 4f else 2.6f,
                center = p
            )
        }
    }
}

@Composable
fun StatRow() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        StatCard("18.7", "mpg", "3-tank average", Modifier.weight(1f))
        StatCard("$0.19", "/mi", "Cost per mile", Modifier.weight(1f))
        StatCard("$56.09", "", "October fuel", Modifier.weight(1f))
    }
}

@Composable
fun StatCard(value: String, unit: String, label: String, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Panel,
        border = androidx.compose.foundation.BorderStroke(1.dp, Line),
        modifier = modifier
    ) {
        Column(Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(value, style = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.Bold, fontSize = 21.sp), color = Ink)
                if (unit.isNotEmpty()) {
                    Text(unit, style = MaterialTheme.typography.labelMedium, color = Dim, modifier = Modifier.padding(start = 2.dp, bottom = 2.dp))
                }
            }
            Text(
                label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = Mut,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun SectionCap(title: String, subtitle: String, padTop: androidx.compose.ui.unit.Dp = 14.dp, padBottom: androidx.compose.ui.unit.Dp = 8.dp) {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = 4.dp).padding(top = padTop, bottom = padBottom),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(title.uppercase(), style = MaterialTheme.typography.labelMedium, color = Mut, letterSpacing = 1.1.sp)
        Text(subtitle, style = MaterialTheme.typography.labelSmall, color = Dim)
    }
}

@Composable
fun FormCard(
    odo: String, onOdo: (String) -> Unit,
    date: String, onDate: (String) -> Unit,
    gallons: String, onGallons: (String) -> Unit,
    total: String, onTotal: (String) -> Unit,
    octane: String, onOctane: (String) -> Unit,
    pricePerGal: String, onPrice: (String) -> Unit,
    station: String, onStation: (String) -> Unit,
    previewMpg: String,
    onSave: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = Panel,
        border = androidx.compose.foundation.BorderStroke(1.dp, Line),
        shadowElevation = 8.dp
    ) {
        Column(Modifier.padding(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                YakiField("Odometer", odo, { onOdo(it.filter { c -> c.isDigit() || c == ',' }.take(8)) }, "197,415", KeyboardType.Number, Modifier.weight(1f))
                YakiField("Date", date, onDate, "", KeyboardType.Text, Modifier.weight(0.74f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                YakiField("Gallons", gallons, { onGallons(it.filter { c -> c.isDigit() || c == '.' }.take(6)) }, "16.4", KeyboardType.Decimal, Modifier.weight(1f))
                YakiField("Total $", total, { onTotal(it.filter { c -> c.isDigit() || c == '.' }.take(8)) }, "56.09", KeyboardType.Decimal, Modifier.weight(1f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                YakiField("Octane", octane, { onOctane(it.filter { c -> c.isDigit() }.take(3)) }, "85", KeyboardType.Number, Modifier.weight(1f))
                YakiField("Price / gal", pricePerGal, { onPrice(it.filter { c -> c.isDigit() || c == '.' }.take(6)) }, "3.42", KeyboardType.Decimal, Modifier.weight(0.74f))
            }
            YakiField("Station — optional", station, onStation, "Costco — Clinton", KeyboardType.Text, Modifier.weight(1f))

            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Color.Transparent,
                border = androidx.compose.foundation.BorderStroke(1.dp, Acc.copy(alpha = 0.28f)),
                modifier = Modifier.fillMaxWidth().padding(top = 2.dp, bottom = 10.dp)
            ) {
                Row(
                    Modifier
                        .background(Brush.linearGradient(listOf(Acc.copy(alpha = 0.09f), Acc.copy(alpha = 0.03f))))
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Estimated efficiency", style = MaterialTheme.typography.bodyMedium, color = Mut)
                    Text("$previewMpg mi/gal", style = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.Bold, fontSize = 17.sp), color = Acc)
                }
            }

            Button(
                onClick = onSave,
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color(0xFF1A0E02)),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(Brush.linearGradient(listOf(Acc, Acc2)), RoundedCornerShape(15.dp))
            ) {
                Text("SAVE FILL-UP", style = MaterialTheme.typography.titleMedium, letterSpacing = 0.5.sp)
            }
        }
    }
}

@Composable
fun TankEntry(entry: TankEntry) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Panel,
        border = androidx.compose.foundation.BorderStroke(1.dp, Line),
        shadowElevation = 6.dp,
        modifier = Modifier.fillMaxWidth().padding(bottom = 9.dp)
    ) {
        Row(
            Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.weight(1f)) {
                Text(entry.station, style = MaterialTheme.typography.titleMedium, color = Ink, fontSize = 14.sp)
                Text(
                    "${entry.date} · ${entry.odometer} · ${entry.gallons} · ${entry.octane} oct",
                    style = MaterialTheme.typography.labelSmall,
                    color = Mut,
                    letterSpacing = 0.4.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Box(
                    Modifier
                        .padding(top = 8.dp)
                        .width(150.dp)
                        .height(4.dp)
                        .background(Line2, RoundedCornerShape(4.dp))
                ) {
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.82f)
                            .background(Brush.horizontalGradient(listOf(Color(0xFFB45812), Acc)), RoundedCornerShape(4.dp))
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("%.1f".format(entry.mpg), style = TextStyle(fontFamily = BarlowSC, fontWeight = FontWeight.Bold, fontSize = 17.sp), color = Acc)
                Text("MPG · $%.2f".format(entry.price), style = MaterialTheme.typography.labelSmall, color = Mut, modifier = Modifier.padding(top = 2.dp))
            }
        }
    }
}
